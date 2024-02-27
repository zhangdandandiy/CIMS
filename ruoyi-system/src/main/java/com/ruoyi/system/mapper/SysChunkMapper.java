package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.SysChunk;

import java.util.List;

/**
 * 分片文件 数据层
 *
 * @author Dandan
 * @date 2023/8/14 16:16
 **/
public interface SysChunkMapper {

    /**
     * 查询文件分片管理
     *
     * @param id 文件分片管理ID
     * @return 文件分片管理
     */
    public SysChunk selectSysChunkById(Long id);

    /**
     * 查询文件分片管理列表
     *
     * @param SysChunk 文件分片管理
     * @return 文件分片管理集合
     */
    public List<SysChunk> selectSysChunkList(SysChunk SysChunk);

    /**
     * 新增文件分片管理
     *
     * @param SysChunk 文件分片管理
     * @return 结果
     */
    public int insertSysChunk(SysChunk SysChunk);

    /**
     * 修改文件分片管理
     *
     * @param SysChunk 文件分片管理
     * @return 结果
     */
    public int updateSysChunk(SysChunk SysChunk);

    /**
     * 删除文件分片管理
     *
     * @param id 文件分片管理ID
     * @return 结果
     */
    public int deleteSysChunkById(Long id);

    /**
     * 功能描述: 根据文件名和MD5值删除chunk记录
     *
     * @param:
     * @return:
     * @author: xjd
     * @date: 2020/7/31 23:43
     */
    int deleteSysChunkByIdentifier(SysChunk SysChunk);

    /**
     * 批量删除文件分片管理
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteSysChunkByIds(Long[] ids);

}
