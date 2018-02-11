package com.tcps.gaowy.basecore.utils;

/**
 * 返回结果封装工具，多用于save，update，delete。
 * 20180111 gaoweiyang
 */
public class ResultUtil {
    private ResultUtil() {
    }

    public static Result success(Object object) {
        Result result = new Result();
        result.setCode(0);
        result.setMsg("成功！");
        result.setData(object);
        return result;
    }

    public static Result success() {
        return success(null);
    }

    public static Result error(Integer code, String msg) {
        Result result = new Result();
        result.setCode(code);
        result.setMsg(msg);
        return result;
    }
}
