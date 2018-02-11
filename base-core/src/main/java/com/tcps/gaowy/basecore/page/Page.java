package com.tcps.gaowy.basecore.page;

import java.util.List;

/**
 * 20180107 gaoweiyang
 *
 * @param <T>
 */
public class Page<T> {
    private PageInfo pageInfo;
    private T bean;
    private List<T> resultList;

    public PageInfo getPageInfo() {
        return pageInfo;
    }

    public void setPageInfo(PageInfo pageInfo) {
        this.pageInfo = pageInfo;
    }

    public List<T> getResultList() {
        return resultList;
    }

    public void setResultList(List<T> resultList) {
        this.resultList = resultList;
    }

    public T getBean() {
        return bean;
    }

    public void setBean(T bean) {
        this.bean = bean;
    }
}
