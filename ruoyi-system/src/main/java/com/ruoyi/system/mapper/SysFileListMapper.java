package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.SysFileList;

import java.util.List;

/**
 * 文件列表 数据层
 *
 * @author Dandan
 * @date 2023/8/14 14:50
 **/
public interface SysFileListMapper {

    /**
     * 查询已上传文件列表
     *
     * @param id 已上传文件列表ID
     * @return 已上传文件列表
     */
    public SysFileList selectSysFileListById(Long id);

    /**
     * 功能描述: 查询单条已上传文件记录
     *
     * @param sysFileList 已上传文件列表
     * @return
     */
    public Integer selectSingleSysFileList(SysFileList sysFileList);

    /**
     * 查询已上传文件列表
     *
     * @param sysFileList 已上传文件列表
     * @return 已上传文件列表集合
     */
    public List<SysFileList> selectSysFileList(SysFileList sysFileList);

    /**
     * 新增已上传文件列表
     *
     * @param sysFileList 已上传文件列表
     * @return 结果
     */
    public int insertSysFileList(SysFileList sysFileList);

    /**
     * 修改已上传文件列表
     *
     * @param sysFileList 已上传文件列表
     * @return 结果
     */
    public int updateSysFileList(SysFileList sysFileList);

    /**
     * 删除已上传文件列表
     *
     * @param id 已上传文件列表Id
     * @return 结果
     */
    public int deleteSysFileListById(Long id);

    /**
     * 批量删除已上传文件列表
     *
     * @param ids 需要删除的数据Id
     * @return 结果
     */
    public int deleteSysFileListByIds(Long[] ids);

}
