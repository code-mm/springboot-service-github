package com.ms.common.base.exception;


import com.ms.common.base.ApiResultCodeMessage;

import lombok.Data;

@Data
public class AppException extends RuntimeException {


    private Integer code;

    private String message;

    public AppException(
            Integer code,
            String message
    ) {
        this.code = code;
        this.message = message;
    }

    public AppException() {
    }

    public AppException(ApiResultCodeMessage apiResultCodeMessage) {
        this.code = apiResultCodeMessage.getCode();
        this.message = apiResultCodeMessage.getMessage();
    }
}
