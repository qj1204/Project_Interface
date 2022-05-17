package com.xiaoxin.projectinterface.common;

import lombok.Data;

import java.io.Serializable;

/**
 * 自定义统一响应体
 */
@Data
public class ResultVO<T> implements Serializable {
    // 状态码，比如100代表响应成功
    private Integer code;

    // 响应信息，用来说明响应情况
    private String msg;

    // 响应的具体数据
    private T data;

    public ResultVO(ResultCode resultCode, T data) {
        this.code = resultCode.getCode();
        this.msg = resultCode.getMsg();
        this.data = data;
    }

    public static <T> ResultVO<T> successResponse(T data){
        return new ResultVO<>(ResultCode.SUCCESS, data);
    }

    public static <T> ResultVO<T> failedResponse(T data){
        return new ResultVO<>(ResultCode.FAILED, data);
    }

    public static <T> ResultVO<T> userLoginErrorResponse(T data){
        return new ResultVO<>(ResultCode.USER_LOGIN_ERROR, data);
    }

    public static <T> ResultVO<T> paramInvalidResponse(T data){
        return new ResultVO<>(ResultCode.PARAM_INVALID, data);
    }

    public static <T> ResultVO<T> paramErrorResponse(T data){
        return new ResultVO<>(ResultCode.PARAM_ERROR, data);
    }

    public static <T> ResultVO<T> userRegErrorResponse(T data){
        return new ResultVO<>(ResultCode.USER_REG_ERROR, data);
    }

    public static <T> ResultVO<T> dataExistedResponse(T data){
        return new ResultVO<>(ResultCode.DATA_EXISTED, data);
    }

    public static <T> ResultVO<T> dataEmptyResponse(T data){
        return new ResultVO<>(ResultCode.DATA_EMPTY, data);
    }

    public static <T> ResultVO<T> identifyErrorResponse(T data){
        return new ResultVO<>(ResultCode.IDENTIFY_ERROR, data);
    }

    public static <T> ResultVO<T> errorResponse(T data){
        return new ResultVO<>(ResultCode.ERROR, data);
    }
}
