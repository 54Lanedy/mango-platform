package com.louis.mango.core.page;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

/**
 * Mybatis分页查询助手
 * Created by liyue
 * Time 2019-09-17 8:30
 */
public class MybatisPageHelper {
    //约定的查询方法名
    public static final String findPage = "findPage";

    /**
     *  误无查询分页查询，约定查询方法名为“findPage”
     * @param pageRequest 分页请求
     * @param mapper   Dao对象，Mybatis的mapper
     * @return
     */
    public static PageResult findPage(PageRequest pageRequest,Object mapper){
        return findPage(pageRequest,mapper,findPage);
    }

    /**
     * 有参数分页查询，调用分页插件进行分页查询
     * @param pageRequest  分页请求
     * @param mapper       Dao对象，Mybatis的mapper
     * @param queryMethodName  要分页的查询方法名
     * @param args  方法参数
     * @return
     */
    @SuppressWarnings({"unchecked","rawtypes"})
    public static PageResult findPage(PageRequest pageRequest,Object mapper,String queryMethodName,Object...args){
        //设置分页参数
        int pageNum = pageRequest.getPageNum();
        int pageSize = pageRequest.getPageSize();
        PageHelper.startPage(pageNum,pageSize);
        //利用反射调用查询方法
        Method method = ReflectionUtils.findMethod(mapper.getClass(),queryMethodName);
        Object result = null;
        try {
            result = method.invoke(mapper, args);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return getPageResult(new PageInfo((List) result));
    }


    /**
     * 将分页信息封装到统一的接口
     * @param pageInfo
     * @return
     */
    private static PageResult getPageResult( PageInfo pageInfo){
        PageResult pageResult=new PageResult();
        pageResult.setPageNum(pageInfo.getPageNum());
        pageResult.setPageSize(pageInfo.getPageSize());
        pageResult.setTotalSize(pageInfo.getTotal());
        pageResult.setTotalPage(pageInfo.getPages());
        pageResult.setContent(pageInfo.getList());
        return pageResult;
    }
}
