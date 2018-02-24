package com.tcps.gaowy.basecore.appexception;


import com.tcps.gaowy.basecore.utils.Result;
import com.tcps.gaowy.basecore.utils.ResultUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
@Slf4j
public class ExceptionHandle {

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Result handleer(Exception e) {
        if (e instanceof ApplicationException) {
            ApplicationException customException = (ApplicationException) e;
            return ResultUtil.error(customException.getCode(), customException.getMsg());
        }
        log.error("系统异常 {}", e);
        return ResultUtil.error(-1, "未知错误");
    }
}
