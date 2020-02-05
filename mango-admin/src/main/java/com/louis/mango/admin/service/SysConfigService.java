package com.louis.mango.admin.service;

import com.louis.mango.admin.model.SysConfig;
import com.louis.mango.core.service.CurdService;

import java.util.List;

/**
 * Created by liyue
 * Time 2020/2/5 11:14
 */
public interface SysConfigService extends CurdService<SysConfig> {

    /**
     * 根据名称查询
     * @param label
     * @return
     */
    List<SysConfig> findByLabel(String label);
}
