package com.example.baselib.net.exception;

/**
 * Created by Administrator on 2018/6/13.
 */

public class ApiException extends RuntimeException {

    private String errorCode;

    public ApiException(String errorCode, String errorMessage) {
        super(errorMessage);
        this.errorCode=errorCode;
    }

    public String getErrorCode() {
        return errorCode;
    }
}
