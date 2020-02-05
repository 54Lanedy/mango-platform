package com.louis.mango.admin.service;

import com.louis.mango.admin.model.SysDept;
import com.louis.mango.core.service.CurdService;

import java.util.List;

/**
 * Created by liyue
 * Time 2020/2/4 18:26
 */
public interface SysDeptService extends CurdService<SysDept> {
    /**
     * 查询机构树
     * @return
     */
    List<SysDept> findTree();
}
