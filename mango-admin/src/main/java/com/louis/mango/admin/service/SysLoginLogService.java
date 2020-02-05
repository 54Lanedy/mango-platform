package com.louis.mango.admin.service;

import com.louis.mango.admin.model.SysLoginLog;
import com.louis.mango.core.service.CurdService;

/**
 * Created by liyue
 * Time 2020/2/5 11:41
 */
public interface SysLoginLogService extends CurdService<SysLoginLog> {
    /**
     * 记录登录日志
     * @param userName
     * @param ip
     * @return
     */
    int writeLoginLog(String userName, String ip);
}
