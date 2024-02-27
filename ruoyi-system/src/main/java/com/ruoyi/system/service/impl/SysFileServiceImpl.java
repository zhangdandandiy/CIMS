package com.ruoyi.system.service.impl;

import com.ruoyi.common.config.RuoYiConfig;
import com.ruoyi.common.constant.FileConstants;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.system.domain.SysChunk;
import com.ruoyi.system.domain.SysFileList;
import com.ruoyi.system.domain.vo.CheckChunkVo;
import com.ruoyi.system.mapper.SysChunkMapper;
import com.ruoyi.system.mapper.SysFileListMapper;
import com.ruoyi.system.service.ISysFileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Dandan
 * @date 2023/8/14 15:13
 **/

@Service
@Slf4j
public class SysFileServiceImpl implements ISysFileService {

    private final static String folderPath = "/file";

    @Autowired
    private SysFileListMapper sysFileListMapper;

    @Autowired
    private SysChunkMapper sysChunkMapper;

    /**
     * 每一个上传块都会包含如下分块信息：
     * chunkNumber: 当前块的次序，第一个块是 1，注意不是从 0 开始的。
     * totalChunks: 文件被分成块的总数。
     * chunkSize: 分块大小，根据 totalSize 和这个值你就可以计算出总共的块数。注意最后一块的大小可能会比这个要大。
     * currentChunkSize: 当前块的大小，实际大小。
     * totalSize: 文件总大小。
     * identifier: 这个就是每个文件的唯一标示,md5码
     * filename: 文件名。
     * relativePath: 文件夹上传的时候文件的相对路径属性。
     * 一个分块可以被上传多次，当然这肯定不是标准行为，但是在实际上传过程中是可能发生这种事情的，这种重传也是本库的特性之一。
     * <p>
     * 根据响应码认为成功或失败的：
     * 200 文件上传完成
     * 201 文加快上传成功
     * 500 第一块上传失败，取消整个文件上传
     * 507 服务器出错自动重试该文件块上传
     */
    @Override
    // 无论抛出何种异常都触发事务回滚
    @Transactional(rollbackFor = Exception.class)
    public int postFileUpload(SysChunk chunk, HttpServletResponse response) {
        int result = FileConstants.UPDATE_FAIL;
        MultipartFile file = chunk.getFile();
        log.debug("file originName: {}, chunkNumber: {}", file.getOriginalFilename(), chunk.getChunkNumber());
        Path path = Paths.get(generatePath(RuoYiConfig.getFilePath() + DateUtils.datePath(), chunk));
        try {
            Files.write(path, chunk.getFile().getBytes());
            log.debug("文件 {} 写入成功, md5:{}", chunk.getFilename(), chunk.getIdentifier());
            // 写入数据库
            result = sysChunkMapper.insertSysChunk(chunk);
        } catch (IOException e) {
            e.printStackTrace();
            response.setStatus(507);
            return FileConstants.UPDATE_FAIL;
        }
        return result;
    }

    @Override
    public CheckChunkVo getFileUpload(SysChunk chunk, HttpServletResponse response) {
        CheckChunkVo chunkVo = new CheckChunkVo();
        // 检查该文件是否存在于fileList中，如果存在，直接返回skipUpload为true，执行闪传
        SysFileList fileList = new SysFileList();
        fileList.setIdentifier(chunk.getIdentifier());
        List<SysFileList> sysFileList = sysFileListMapper.selectSysFileList(fileList);
        if (sysFileList != null && !sysFileList.isEmpty()) {
            response.setStatus(HttpServletResponse.SC_CREATED);
            chunkVo.setSkipUpload(true);
            return chunkVo;
        }

        SysChunk resultChunk = new SysChunk();
        resultChunk.setIdentifier(chunk.getIdentifier());
        List<SysChunk> chunks = sysChunkMapper.selectSysChunkList(resultChunk);
        // 将已存在的块的chunkNumber列表返回给前端，前端规避掉这些快
        if (chunks != null && !chunks.isEmpty()) {
            List<Integer> collect = chunks.stream().map(SysChunk::getChunkNumber).collect(Collectors.toList());
            chunkVo.setUploaded(collect);
        }
        return chunkVo;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int mergeFile(SysFileList fileInfo) {
        String fileName = fileInfo.getFilename();
        String file = RuoYiConfig.getFilePath() + DateUtils.datePath() + "/" + fileInfo.getIdentifier() + "/" + fileName;
        String folder = RuoYiConfig.getFilePath() + DateUtils.datePath() + "/" + fileInfo.getIdentifier();
        String url = folderPath + "/" + fileInfo.getIdentifier() + "/" + fileName;

        merge(file, folder, fileName);
        // 当文件已存在数据库时，返回已存在标识
        if (sysFileListMapper.selectSingleSysFileList(fileInfo) > 0) {
            return FileConstants.UPDATE_EXISTS;
        }
        fileInfo.setLocation(file);
        fileInfo.setUrl(url);
        int i = sysFileListMapper.insertSysFileList(fileInfo);
        if (i > 0) {
            // 插入文件记录成功后，删除chunk表中的对应记录，释放空间
            SysChunk chunk = new SysChunk();
            chunk.setIdentifier(fileInfo.getIdentifier());
            chunk.setFilename(fileInfo.getFilename());
            sysChunkMapper.deleteSysChunkByIdentifier(chunk);
        }
        return i;
    }

    /**
     * 功能描述:生成块文件所在地址
     *
     * @param uploadFolder
     * @param chunk
     * @return
     */
    private String generatePath(String uploadFolder, SysChunk chunk) {
        StringBuilder stringBuilder = new StringBuilder();
        // 文件夹地址 / md5
        stringBuilder.append(uploadFolder).append("/").append(chunk.getIdentifier());
        // 判断 uploadFolder/identifier 路径是否存在，不存在则创建
        if (!Files.isWritable(Paths.get(stringBuilder.toString()))) {
            log.info("path not exist,create path: {}", stringBuilder);
            try {
                Files.createDirectories(Paths.get(stringBuilder.toString()));
            } catch (Exception e) {
                log.error(e.getMessage(), e);
            }
        }
        // 文件夹地址/md5/文件名-1 (分片文件名)
        return stringBuilder.append("/")
                .append(chunk.getFilename())
                .append("-")
                .append(chunk.getChunkNumber()).toString();
    }

    public static void merge(String targetFile, String folder, String fileName) {
        try {
            Files.createFile(Paths.get(targetFile));
            Files.list(Paths.get(folder))
                    .filter(path -> !path.getFileName().toString().equals(fileName))
                    .sorted((o1, o2) -> {
                        String p1 = o1.getFileName().toString();
                        String p2 = o2.getFileName().toString();
                        int i1 = p1.lastIndexOf("-");
                        int i2 = p2.lastIndexOf("-");
                        return Integer.valueOf(p2.substring(i2)).compareTo(Integer.valueOf(p1.substring(i1)));
                    }).forEach(path -> {
                        try {
                            // 以追加的形式写入文件
                            Files.write(Paths.get(targetFile), Files.readAllBytes(path), StandardOpenOption.APPEND);
                            // 合并后删除该块
                            Files.delete(path);
                        } catch (IOException e) {
                            log.error(e.getMessage(), e);
                        }
                    });
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
    }

}
