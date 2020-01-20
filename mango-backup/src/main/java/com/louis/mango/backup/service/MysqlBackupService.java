package com.louis.mango.backup.service;

/**
 * MySql命令行备份/恢复服务
 * Created by liyue
 * Time 2019-09-23 16:15
 */
public interface MysqlBackupService {

    /**
     * 备份数据库
     * @param host  地址，远程或本机
     * @param userName  数据库的用户名
     * @param password  数据库的密码
     * @param backupFolderPath  备份的路径
     * @param fileName  备份的文件名
     * @param database  需要备份的数据库名
     * @return
     * @throws Exception
     */
    boolean backup(String host, String userName, String password,
                   String backupFolderPath,String fileName, String database) throws Exception;

    /**
     * 还原数据库
     * @param restoreFilePath 数据库备份的脚本路径
     * @param host     ip地址
     * @param userName 数据库用户名
     * @param password 数据库密码
     * @param database 数据库名称
     * @return
     * @throws Exception
     */
    boolean restore(String restoreFilePath,String host,String userName,String password,String database) throws Exception;
}
