package com.ruoyi.system.service;

import com.ruoyi.system.domain.SysChunk;
import com.ruoyi.system.domain.SysFileList;
import com.ruoyi.system.domain.vo.CheckChunkVo;

import javax.servlet.http.HttpServletResponse;

/**
 * @author Dandan
 * @date 2023/8/14 15:08
 **/
public interface ISysFileService {

    int postFileUpload(SysChunk chunk, HttpServletResponse response);

    CheckChunkVo getFileUpload(SysChunk chunk, HttpServletResponse response);

    int mergeFile(SysFileList fileInfo);

}
