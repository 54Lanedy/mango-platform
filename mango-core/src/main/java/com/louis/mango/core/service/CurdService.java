package com.louis.mango.core.service;

import com.louis.mango.core.page.PageRequest;
import com.louis.mango.core.page.PageResult;

import java.util.List;

/**
 * 通用CURD接口
 * Created by liyue
 * Time 2019-09-17 8:20
 */
public interface CurdService<T> {
    /**
     * 保存操作
     */
    int save(T record);

    /**
     * 删除操作
     */
    int delete(T record);

    /**
     * 批量删除
     */
    int delete (List<T> records);

    /**
     * 根据id查询
     */
    T findById(Long id);

    /**
     * 分页查询
     * @param pageRequest 自定义，统一分页查询请求
     * @return  PageResult 自定义，统一分页查询结果
     */
    PageResult findPage(PageRequest pageRequest);
}
