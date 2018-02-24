package com.tcps.gaowy.basecore.appexception;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 自定义异常类
 * 20180101 gaoweiyang
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class ApplicationException extends RuntimeException {
    private Integer code;
    private String msg;

    public ApplicationException(Integer code, String msg) {
        super(msg);
        this.code = code;
        this.msg = msg;
    }
}
