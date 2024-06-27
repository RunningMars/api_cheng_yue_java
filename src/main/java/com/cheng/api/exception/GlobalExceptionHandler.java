package com.cheng.api.exception;

import com.cheng.api.common.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {



//    /**
//     * 运行时异常拦截
//     */
//    @ExceptionHandler(RuntimeException.class)
//    @ResponseBody
//    public R<?> exceptionHandler(RuntimeException e){
//        StackTraceElement[] stackTrace = e.getStackTrace();
//        System.out.println(e.getMessage());
//        return R.error("系统异常");
//    }

    /**
     * 自定义异常拦截
     */
    @ExceptionHandler(value = CustomException.class)
    @ResponseBody
    public R<?> customerExceptionHandler(CustomException e){
        return R.error(e.getCode(), e.getMessage());
    }


    /**
     * 自定义异常拦截
     */
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    @ResponseBody
    public R<?> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e){
        List<FieldError> fieldErrors = e.getBindingResult().getFieldErrors();
        String defaultMessage = fieldErrors.get(0).getDefaultMessage();
        log.error("捕获 MethodArgumentNotValidException , 原因: " + defaultMessage);
        return R.abort( defaultMessage);
    }

}
