package com.ruoyi.system.mapper;

import com.ruoyi.common.core.domain.entity.SysRole;
import com.ruoyi.system.domain.SysDeptRole;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author Dandan
 * @date 2024/1/6 19:10
 **/

@Mapper
public interface SysDeptRoleMapper {

    /**
     * 新增部门和角色关联
     *
     * @param sysDeptRole 部门和角色关联
     * @return 结果
     */
    public int insertSysDeptRole(SysDeptRole sysDeptRole);

    /**
     * 修改部门和角色关联
     *
     * @param sysDeptRole 部门和角色关联
     * @return 结果
     */
    public int updateSysDeptRole(SysDeptRole sysDeptRole);

    /**
     * 根据部门ID删除部门和角色关联信息
     *
     * @param deptId
     * @return
     */
    public int deleteSysDeptRoleByDeptId(Long deptId);

    /**
     * 根据部门ID查询相对应的角色
     *
     * @param deptId
     * @return
     */
    public Long getSysUserRoleId(Long deptId);

}
