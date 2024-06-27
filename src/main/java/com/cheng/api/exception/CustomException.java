package com.cheng.api.exception;

import lombok.Getter;

@Getter
public class CustomException extends RuntimeException{

    private final Integer code;

    public CustomException(Integer code,String msg){
        super(msg);
        this.code = code;
    }

    public CustomException(String msg){
        super(msg);
        this.code = 500;
    }
}
