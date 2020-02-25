package com.louis.mango.admin.service;

import com.louis.mango.admin.model.SysUser;
import com.louis.mango.admin.model.SysUserRole;
import com.louis.mango.core.page.PageRequest;
import com.louis.mango.core.service.CurdService;

import java.io.File;
import java.util.List;
import java.util.Set;

/**
 * Created by liyue
 * Time 2019-09-16 8:52
 */
public interface SysUserService extends CurdService<SysUser> {

    SysUser findByName(String username);

    /**
     * 查找用户的菜单权限标识集合
     * @param userName
     * @return
     */
    Set<String> findPermissions(String userName);

    /**
     * 查找用户的角色集合
     * @param userId
     * @return
     */
    List<SysUserRole> findUserRoles(Long userId);

    /**
     * 生成用户信息Excel文件
     * @param pageRequest 要导出的分页查询参数
     * @return
     */
    File createUserExcelFile(PageRequest pageRequest);

    /**
     * 查询全部
     */
    List<SysUser> findAll();
}
