package com.tcps.gaowy.basecore.utils;

import lombok.Data;

/**
 * 返回结果封装类。
 * 20180102 gaoweiyang
 *
 * @param <T>
 */
@Data
public class Result<T> {

    private Integer code;
    private String msg;
    private T data;

}