package com.ruoyi.system.service;

import com.ruoyi.system.domain.SysFileList;

import java.util.List;

/**
 * 已上传文件列表
 *
 * @author Dandan
 * @date 2023/8/15 8:58
 **/
public interface ISysFileListService {

    /**
     * 查询已上传文件列表
     *
     * @param id 已上传文件列表ID
     * @return 已上传文件列表
     */
    public SysFileList selectSysFileListById(Long id);

    /**
     * 查询已上传文件列表列表
     *
     * @param SysFileList 已上传文件列表
     * @return 已上传文件列表集合
     */
    public List<SysFileList> selectSysFileListList(SysFileList SysFileList);

    /**
     * 新增已上传文件列表
     *
     * @param SysFileList 已上传文件列表
     * @return 结果
     */
    public int insertSysFileList(SysFileList SysFileList);

    /**
     * 修改已上传文件列表
     *
     * @param SysFileList 已上传文件列表
     * @return 结果
     */
    public int updateSysFileList(SysFileList SysFileList);

    /**
     * 批量删除已上传文件列表
     *
     * @param ids 需要删除的已上传文件列表ID
     * @return 结果
     */
    public int deleteSysFileListByIds(Long[] ids);

    /**
     * 删除已上传文件列表信息
     *
     * @param id 已上传文件列表ID
     * @return 结果
     */
    public int deleteSysFileListById(Long id);

}
