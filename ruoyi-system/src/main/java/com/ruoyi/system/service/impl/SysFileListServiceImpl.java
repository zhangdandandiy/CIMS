package com.ruoyi.system.service.impl;

import com.ruoyi.system.domain.SysFileList;
import com.ruoyi.system.mapper.SysFileListMapper;
import com.ruoyi.system.service.ISysFileListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Dandan
 * @date 2023/8/15 9:03
 **/

@Service
public class SysFileListServiceImpl implements ISysFileListService {

    @Autowired
    private SysFileListMapper sysFileListMapper;

    /**
     * 查询已上传文件列表
     *
     * @param id 已上传文件列表ID
     * @return 已上传文件列表
     */
    @Override
    public SysFileList selectSysFileListById(Long id) {
        return sysFileListMapper.selectSysFileListById(id);
    }

    /**
     * 查询已上传文件列表列表
     *
     * @param sysFileList 已上传文件列表
     * @return 已上传文件列表
     */
    @Override
    public List<SysFileList> selectSysFileListList(SysFileList sysFileList) {
        return sysFileListMapper.selectSysFileList(sysFileList);
    }

    /**
     * 新增已上传文件列表
     *
     * @param sysFileList 已上传文件列表
     * @return 结果
     */
    @Override
    public int insertSysFileList(SysFileList sysFileList) {
        return sysFileListMapper.insertSysFileList(sysFileList);
    }

    /**
     * 修改已上传文件列表
     *
     * @param sysFileList 已上传文件列表
     * @return 结果
     */
    @Override
    public int updateSysFileList(SysFileList sysFileList) {
        return sysFileListMapper.updateSysFileList(sysFileList);
    }

    /**
     * 批量删除已上传文件列表
     *
     * @param ids 需要删除的已上传文件列表ID
     * @return 结果
     */
    @Override
    public int deleteSysFileListByIds(Long[] ids) {
        return sysFileListMapper.deleteSysFileListByIds(ids);
    }

    /**
     * 删除已上传文件列表信息
     *
     * @param id 已上传文件列表ID
     * @return 结果
     */
    @Override
    public int deleteSysFileListById(Long id) {
        return sysFileListMapper.deleteSysFileListById(id);
    }

}
