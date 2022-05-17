package com.xiaoxin.projectinterface.common;

import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLIntegrityConstraintViolationException;

/**
 * 全局异常处理
 */
@RestControllerAdvice
public class ExceptionControllerAdvice {

//    @ExceptionHandler(BindException.class)
//    public ResultVO<String> BindExceptionHandler(BindException e) {
//        ObjectError objectError = e.getBindingResult().getAllErrors().get(0);
//        String errorMsg = objectError.getDefaultMessage();
//
//        if("用户邮箱不能为空".equals(errorMsg))
//            return new ResultVO<>(ResultCode.PARAM_IS_BLANK, errorMsg);
//        else if ("邮箱格式不正确".equals(errorMsg))
//            return new ResultVO<>(ResultCode.PARAM_IS_INVALID, errorMsg);
//        else
//            return new ResultVO<>(ResultCode.VALIDATE_FAILED, errorMsg);
//    }

    @ExceptionHandler(NullPointerException.class)
    public ResultVO<String> NullPointExceptionHandler() {
        return ResultVO.failedResponse("空指针异常");
    }

    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public ResultVO<String> SQLIntegrityConstraintViolationExceptionHandler() {
        return ResultVO.failedResponse("SQL完整性约束违反异常");
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResultVO<String> IllegalArgumentExceptionHandler() {
        return ResultVO.failedResponse("非法参数异常");
    }

    @ExceptionHandler(IndexOutOfBoundsException.class)
    public ResultVO<String> IndexOutOfBoundsExceptionHandler() {
        return ResultVO.failedResponse("索引越界异常");
    }

    @ExceptionHandler(Exception.class)
    public ResultVO<String> ExceptionHandler() {
        return ResultVO.errorResponse("异常申请");
    }

}
