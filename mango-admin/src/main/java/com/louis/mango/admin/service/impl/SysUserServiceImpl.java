package com.louis.mango.admin.service.impl;

import com.louis.mango.admin.dao.SysDeptMapper;
import com.louis.mango.admin.dao.SysRoleMapper;
import com.louis.mango.admin.dao.SysUserMapper;
import com.louis.mango.admin.dao.SysUserRoleMapper;
import com.louis.mango.admin.model.*;
import com.louis.mango.admin.service.SysMenuService;
import com.louis.mango.admin.service.SysUserService;
import com.louis.mango.common.utils.PoiUtils;
import com.louis.mango.core.page.MybatisPageHelper;
import com.louis.mango.core.page.PageRequest;
import com.louis.mango.core.page.PageResult;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.*;

/**
 * Created by liyue
 * Time 2019-09-16 8:54
 */
@Service
public class SysUserServiceImpl implements SysUserService {
    @Autowired
    private SysUserMapper sysUserMapper;
    @Autowired
    private SysMenuService sysMenuService;
    @Autowired
    private SysUserRoleMapper sysUserRoleMapper;
    @Autowired
    private SysRoleMapper sysRoleMapper;
    @Autowired
    private SysDeptMapper sysDeptMapper;

    @Override
    public List<SysUser> findAll() {
        return sysUserMapper.findAll();
    }

    @Override
    public SysUser findByName(String username) {
        return sysUserMapper.findByName(username);
    }

    @Override
    public Set<String> findPermissions(String userName) {
        Set<String> perms = new HashSet<>();
        List<SysMenu> sysMenus = sysMenuService.findByUser(userName);
        for(SysMenu sysMenu:sysMenus) {
            if(sysMenu.getPerms() != null && !"".equals(sysMenu.getPerms())) {
                perms.add(sysMenu.getPerms());
            }
        }
        return perms;
    }

    @Override
    public List<SysUserRole> findUserRoles(Long userId) {
        return sysUserRoleMapper.findUserRoles(userId);
    }

    @Override
    public File createUserExcelFile(PageRequest pageRequest) {
        PageResult pageResult = findPage(pageRequest);
        return createUserExcelFile(pageResult.getContent());
    }

    private static File createUserExcelFile(List<?> records) {
        if (records == null) {
            records = new ArrayList<>();
        }
        //创建Excel
        Workbook workbook=new XSSFWorkbook();
        //创建表
        Sheet sheet = workbook.createSheet();
        //创建表头
        Row row0 = sheet.createRow(0);
        int columnIndex = 0;
        row0.createCell(columnIndex).setCellValue("No");
        row0.createCell(++columnIndex).setCellValue("ID");
        row0.createCell(++columnIndex).setCellValue("用户名");
        row0.createCell(++columnIndex).setCellValue("昵称");
        row0.createCell(++columnIndex).setCellValue("机构");
        row0.createCell(++columnIndex).setCellValue("角色");
        row0.createCell(++columnIndex).setCellValue("邮箱");
        row0.createCell(++columnIndex).setCellValue("手机号");
        row0.createCell(++columnIndex).setCellValue("状态");
        row0.createCell(++columnIndex).setCellValue("头像");
        row0.createCell(++columnIndex).setCellValue("创建人");
        row0.createCell(++columnIndex).setCellValue("创建时间");
        row0.createCell(++columnIndex).setCellValue("最后更新人");
        row0.createCell(++columnIndex).setCellValue("最后更新时间");
        //创建数据主体
        for (int i = 0; i < records.size(); i++) {
            SysUser user = (SysUser)records.get(i);
            Row row = sheet.createRow(i + 1);
            //从第二行开始创建
            for (int j = 0; j < columnIndex + 1; j++) {
                row.createCell(j);
            }
            columnIndex = 0;
            row.getCell(columnIndex).setCellValue(i+1);
            row.getCell(++columnIndex).setCellValue(user.getId());
            row.getCell(++columnIndex).setCellValue(user.getName());
            row.getCell(++columnIndex).setCellValue(user.getNickName());
            row.getCell(++columnIndex).setCellValue(user.getDeptId());
            row.getCell(++columnIndex).setCellValue(user.getDelFlag());
//            row.getCell(++columnIndex).setCellValue(user.getDeptName());
//            row.getCell(++columnIndex).setCellValue(user.getRoleName());
            row.getCell(++columnIndex).setCellValue(user.getEmail());
            row.getCell(++columnIndex).setCellValue(user.getMobile());
            row.getCell(++columnIndex).setCellValue(user.getStatus());
            row.getCell(++columnIndex).setCellValue(user.getAvatar());
            row.getCell(++columnIndex).setCellValue(user.getCreateBy());
            row.getCell(++columnIndex).setCellValue(user.getCreateTime());
            row.getCell(++columnIndex).setCellValue(user.getLastUpdateBy());
            row.getCell(++columnIndex).setCellValue("");
        }
        return PoiUtils.createExcelFile(workbook,"download_user");
    }

    @Override
    public int save(SysUser record) {
        if (record.getId() == null || record.getId() == 0) {
            return sysUserMapper.insertSelective(record);
        }
        return sysUserMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int delete(SysUser record) {
        return sysUserMapper.deleteByPrimaryKey(record.getId());
    }

    @Override
    public int delete(List<SysUser> records) {
        for (SysUser record : records) {
            delete(record);
        }
        return 1;
    }

    @Override
    public SysUser findById(Long id) {
        return sysUserMapper.selectByPrimaryKey(id);
    }

    @Override
    public PageResult findPage(PageRequest pageRequest) {
        PageResult pageResult = null;
        Object name = pageRequest.getParamValue("name");
        Object email = pageRequest.getParamValue("email");
        if(name != null) {
            if(email != null) {
                pageResult = MybatisPageHelper.findPage(pageRequest, sysUserMapper, "findPageByNameAndEmail", name, email);
            } else {
                pageResult = MybatisPageHelper.findPage(pageRequest, sysUserMapper, "findPageByName", name);
            }
        } else {
            pageResult = MybatisPageHelper.findPage(pageRequest, sysUserMapper);
        }
        // 加载用户角色信息
        findUserRoles(pageResult);
        return pageResult;
    }

    /**
     * 加载用户角色
     * @param pageResult
     */
    private void findUserRoles(PageResult pageResult) {
        List<?> content = pageResult.getContent();
        for (Object object : content) {
            SysUser sysUser = (SysUser) object;
            List<SysUserRole> userRoles = findUserRoles(sysUser.getId());
            sysUser.setUserRoles(userRoles);
            sysUser.setRoleNames(getRoleNames(userRoles));
            sysUser.setDeptName(getDeptName(sysUser.getDeptId()));
        }
    }

    //获取用户的角色名称
    private String getRoleNames(List<SysUserRole> userRoles) {
        StringBuilder sb = new StringBuilder();
        for(Iterator<SysUserRole> iter = userRoles.iterator(); iter.hasNext();) {
            SysUserRole userRole = iter.next();
            SysRole sysRole = sysRoleMapper.selectByPrimaryKey(userRole.getRoleId());
            if(sysRole == null) {
                continue ;
            }
            sb.append(sysRole.getRemark());
            if(iter.hasNext()) {
                sb.append(", ");
            }
        }
        return sb.toString();
    }

    //获取用户的部门名称
    private String getDeptName(Long deptId) {
        SysDept dept = sysDeptMapper.selectByPrimaryKey(deptId);
        return dept.getName();
    }
}
