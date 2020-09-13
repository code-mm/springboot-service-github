package com.ms.common.base;


import com.ms.common.base.exception.AppException;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ApiResult<T> {

    private Integer code;

    private String message;

    private T data;

    public void setResultCodeMessage(ApiResultCodeMessage apiResultCodeMessage) {
        this.setCode(apiResultCodeMessage.getCode());
        this.setMessage(apiResultCodeMessage.getMessage());
    }

    public static ApiResult<Object> success(Object data) {
        ApiResult<Object> result = new ApiResult();
        result.setCode(ApiResultCodeMessage.SUCCESS.getCode());
        result.setMessage(ApiResultCodeMessage.SUCCESS.getMessage());
        result.setData(data);
        return result;
    }

    public static ApiResult<Object> filure(Exception e) {
        ApiResult<Object> result = new ApiResult();
        result.setCode(ApiResultCodeMessage.FILURE.getCode());
        if (e != null) {
            result.setMessage(e.getMessage());
        } else {
            result.setMessage("null");
        }
        return result;
    }

    public static ApiResult<Object> filure(AppException e) {
        ApiResult<Object> result = new ApiResult();
        result.setCode(e.getCode());
        result.setMessage(e.getMessage());
        return result;
    }


    public ApiResult<T> ok(T data) {
        ApiResult<T> result = new ApiResult();
        result.setCode(ApiResultCodeMessage.SUCCESS.getCode());
        result.setMessage(ApiResultCodeMessage.SUCCESS.getMessage());
        return result;
    }

    public ApiResult<T> ok() {
        ApiResult<T> result = new ApiResult();
        result.setCode(ApiResultCodeMessage.SUCCESS.getCode());
        result.setMessage(ApiResultCodeMessage.SUCCESS.getMessage());
        return result;
    }

    public ApiResult<T> error(AppException e) {
        ApiResult<T> result = new ApiResult();
        result.setCode(e.getCode());
        result.setMessage(e.getMessage());
        return result;
    }

    public ApiResult<T> error() {
        ApiResult<T> result = new ApiResult();
        return result;
    }
}
