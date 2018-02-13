package com.tcps.gaowy.basecore.page;

import java.io.Serializable;

/**
 * 结果封装Result 实例中。
 * 数据与分页类分离。
 * 20180109 gaoweiyang
 */
public class PageInfo implements Serializable {
    private Integer pageNo = 1;
    private Integer pageSize = 10;
    private String orderBy;

    private Integer totalCount = 0;

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        if (pageNo < 1) {
            this.pageNo = 1;    //默认页数1.
        } else {
            this.pageNo = pageNo;
        }
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        if (pageSize < 1) {
            this.pageSize = 1;    //默认显示10个.
        } else {
            this.pageSize = pageSize;
        }
    }

    public Integer getPageStartIndex() {
        return (this.pageNo - 1) * this.pageSize;
    }

    public Integer getPageEndIndex() {
        int end = this.getPageStartIndex() + this.getPageSize();  //不包含最后一条记录-1
        if (end > this.getTotalCount()) {
            end = this.getPageStartIndex() + (this.getTotalCount() % this.getPageSize());
        }
        return end;
    }

    public Integer getTotalPage() {
        if (this.totalCount % this.pageSize == 0) {
            return this.totalCount / this.pageSize;
        }
        return this.totalCount / this.pageSize + 1;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

}