package com.sky.handler;

import com.sky.constant.MessageConstant;
import com.sky.exception.BaseException;
import com.sky.result.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLIntegrityConstraintViolationException;

/**
 * 全局异常处理器，处理项目中抛出的业务异常
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    /**
     * 捕获业务异常
     * @param ex
     * @return
     */
    @ExceptionHandler
    public Result exceptionHandler(BaseException ex){
        log.error("异常信息：{}", ex.getMessage());
        return Result.error(ex.getMessage());
    }


    @ExceptionHandler
    public Result exceptionHandler(SQLIntegrityConstraintViolationException e) {
        //Duplicate entry 'zhangsan' for key 'employee.idx_username';
        log.error("异常信息：{}",e.getMessage());
        String message = e.getMessage();
        if(message.contains("Duplicate entry")){
            //下面的就是分割上面的字符串，获取到动态的用户名
            String[]split=message.split(" ");
            String userName=split[2];
            String mes=userName+ MessageConstant.ALREADY_EXISTS;//这里引入报错原因（常量）“已存在”，它就会拼接起来
            return Result.error(mes);
        }
        return Result.error(MessageConstant.UNKNOWN_ERROR);
    }

}
