package com.louis.mango.admin.dao;

import com.louis.mango.admin.model.SysUserRole;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysUserRoleMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SysUserRole record);

    int insertSelective(SysUserRole record);

    SysUserRole selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SysUserRole record);

    int updateByPrimaryKey(SysUserRole record);

    List<SysUserRole> findUserRoles(@Param(value="userId") Long userId);
    int deleteByUserId(@Param(value="userId") Long userId);
}