package com.louis.mango.admin.service;

import com.louis.mango.admin.model.SysUser;
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
    /**
     * 查询全部
     */
    List<SysUser> findAll();

    /**
     * 生成用户信息Excel文件
     * @param pageRequest
     * @return
     */
    File createUserExcelFile (PageRequest pageRequest);

    /**
     * 根据用户名查询
     * @param username
     */
    SysUser findByName(String username);

    Set<String> findPermissions(String name);
}
