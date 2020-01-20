package com.louis.mango.backup.service.impl;

import com.louis.mango.backup.service.MysqlBackupService;
import com.louis.mango.backup.util.MySqlBackupRestoreUtils;
import org.springframework.stereotype.Service;

/**
 * 备份还原实现
 * Created by liyue
 * Time 2019-09-23 16:22
 */
@Service
public class MysqlBackupServiceImpl implements MysqlBackupService {
    @Override
    public boolean backup(String host, String userName, String password, String backupFolderPath, String fileName, String database) throws Exception {

        return MySqlBackupRestoreUtils.backup(host,userName,password,backupFolderPath,fileName,database);
    }

    @Override
    public boolean restore(String restoreFilePath, String host, String userName, String password, String database) throws Exception {
        return MySqlBackupRestoreUtils.restore(restoreFilePath,host,userName,password,database);
    }
}
