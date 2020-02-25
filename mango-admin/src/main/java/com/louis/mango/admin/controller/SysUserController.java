package com.louis.mango.admin.controller;

import ch.qos.logback.core.net.AbstractSSLSocketAppender;
import com.louis.mango.admin.constant.SysConstants;
import com.louis.mango.admin.model.SysUser;
import com.louis.mango.admin.service.SysUserService;
import com.louis.mango.admin.util.PasswordUtils;
import com.louis.mango.common.utils.FileUtils;
import com.louis.mango.core.http.HttpResult;
import com.louis.mango.core.page.PageRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.File;

/**
 * Created by liyue
 * Time 2019-09-16 8:55
 */
@RestController
@RequestMapping("user")
public class SysUserController {
    @Autowired
    private SysUserService sysUserService;

//    @PreAuthorize("hasAuthority('sys:user:add') AND hasAuthority('sys:user:edit')")
    @PostMapping("/save")
    public HttpResult save(@RequestBody SysUser record) {
        SysUser user = sysUserService.findById(record.getId());
        if (user != null) {
            //超级管理员不能修改
            if (SysConstants.ADMIN.equalsIgnoreCase(user.getName())) {
                return HttpResult.error("超级管理员不允许修改");
            }
        }
        if (record.getPassword() != null) {
            String salt = PasswordUtils.getSalt();
            if (user == null) {
                //新增用户
                if (sysUserService.findByName(record.getName()) != null) {
                    return HttpResult.error("用户名已存在！");
                }
                String password = PasswordUtils.encode(record.getPassword(), salt);
                record.setPassword(password);
                record.setSalt(salt);
            }else{
                //修改用户信息,且修改了密码
                if (!record.getPassword().equals(user.getPassword())) {
                    String password = PasswordUtils.encode(record.getPassword(), salt);
                    record.setPassword(password);
                    record.setSalt(salt);
                }
            }
        }
        return HttpResult.ok(sysUserService.save(record));
    }

    @GetMapping(value = "/findAll")
    @PreAuthorize("hasAuthority('sys:user:view')")
    public Object findAll(){
        return sysUserService.findAll();
    }

    @PostMapping(value = "/findPage")
    public HttpResult findPage(@RequestBody PageRequest pageRequest){

        return HttpResult.ok(sysUserService.findPage(pageRequest));
    }

    @PostMapping(value = "/exportExcelUser")
    public void exportExcelUser(@RequestBody PageRequest pageRequest, HttpServletResponse res){
        File file = sysUserService.createUserExcelFile(pageRequest);
        FileUtils.downloadFile(res,file,file.getName());
    }

//    @PreAuthorize("hasAuthority('sys:user:view')")
    @GetMapping(value="/findPermissions")
    public HttpResult findPermissions(@RequestParam String name) {
        return HttpResult.ok(sysUserService.findPermissions(name));
    }
}
