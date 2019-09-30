package com.louis.mango.core.page;

import java.util.List;

/**
 * 分页结果封装
 * Created by liyue
 * Time 2019-09-17 8:25
 */
public class PageResult {
    //当前页码
    private int pageNum;
    //每页数量
    private int pageSize;
    //记录总数
    private long totalSize;
    //页码总数
    private int totalPage;
    //分页数据
    private List<?> content;

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public long getTotalSize() {
        return totalSize;
    }

    public void setTotalSize(long totalSize) {
        this.totalSize = totalSize;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public List<?> getContent() {
        return content;
    }

    public void setContent(List<?> content) {
        this.content = content;
    }
}
