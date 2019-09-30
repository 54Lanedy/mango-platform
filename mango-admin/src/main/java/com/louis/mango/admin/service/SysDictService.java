package com.louis.mango.admin.service;

import com.louis.mango.admin.model.SysDict;
import com.louis.mango.core.service.CurdService;

import java.util.List;

/**
 * 字典管理业务接口
 * Created by liyue
 * Time 2019-09-17 11:29
 */
public interface SysDictService extends CurdService<SysDict> {
    /**
     * 根据标签名称查询
     * @return
     */
    List<SysDict> findByLabel(String label);
}
