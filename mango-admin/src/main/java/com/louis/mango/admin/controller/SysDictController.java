package com.louis.mango.admin.controller;

import com.louis.mango.admin.dao.SysDictMapper;
import com.louis.mango.admin.model.SysDict;
import com.louis.mango.admin.service.SysDictService;
import com.louis.mango.core.http.HttpResult;
import com.louis.mango.core.page.PageRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by liyue
 * Time 2019-09-18 10:05
 */
@RestController
@RequestMapping(value = "dict")
public class SysDictController {

    @Autowired
    private SysDictService sysDictService;

    @PostMapping(value = "/save")
    @PreAuthorize("hasAuthority('sys:dict:add') and hasAuthority('sys:dict:edit')")
    public HttpResult save(@RequestBody SysDict record){
        return HttpResult.ok(sysDictService.save(record));
    }

    @PostMapping(value = "/delete")
    @PreAuthorize("hasAuthority('sys:dict:delete')")
    public HttpResult delete(@RequestBody List<SysDict> records){
        return HttpResult.ok(sysDictService.delete(records));
    }
//    @PreAuthorize("hasAuthority('sys:dict:view')")
    @PostMapping(value = "/findPage")
    public HttpResult findPage(@RequestBody PageRequest pageRequest){
        return HttpResult.ok(sysDictService.findPage(pageRequest));
    }

//    @PreAuthorize("hasAuthority('sys:dict:view')")
    @PostMapping(value = "/findByLabel")
    public HttpResult findByLabel(@RequestBody String label){
        return HttpResult.ok(sysDictService.findByLabel(label));
    }

}
