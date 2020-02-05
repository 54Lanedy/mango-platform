package com.louis.mango.admin.service.impl;

import com.louis.mango.admin.dao.SysLogMapper;
import com.louis.mango.admin.model.SysLog;
import com.louis.mango.admin.service.SysLogService;
import com.louis.mango.core.page.MybatisPageHelper;
import com.louis.mango.core.page.PageRequest;
import com.louis.mango.core.page.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * Created by liyue
 * Time 2020/2/5 15:07
 */
@Service
public class SysLogServiceImpl implements SysLogService {

    @Autowired
    private SysLogMapper sysLogMapper;

    @Override
    public int save(SysLog record) {
        if (record.getId() == null && record.getId() == 0) {
            return sysLogMapper.insertSelective(record);
        }
        return sysLogMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int delete(SysLog record) {
        return sysLogMapper.deleteByPrimaryKey(record.getId());
    }

    @Override
    public int delete(List<SysLog> records) {
        for (SysLog record : records) {
            delete(record);
        }
        return 1;
    }

    @Override
    public SysLog findById(Long id) {
        return sysLogMapper.selectByPrimaryKey(id);
    }

    @Override
    public PageResult findPage(PageRequest pageRequest) {
        Object label = pageRequest.getParamValue("userName");
//        if(!StringUtils.isEmpty(label)) {
//            return MybatisPageHelper.findPage(pageRequest, sysLogMapper, "findPageByUserName", label);
//        }
        return MybatisPageHelper.findPage(pageRequest, sysLogMapper);
    }
}
