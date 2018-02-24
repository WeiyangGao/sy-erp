package com.tcps.gaowy.basecore.dao;

/**
 * domain 基类 用于获得子类表名。
 * 20180110 gaoweiyang
 */
public abstract class BaseDO {

    //子类可以通过接口设置自己的表名。
    //其他类可以通过这个方法获得DTO实例的表名。
    public abstract String tableName();
}
