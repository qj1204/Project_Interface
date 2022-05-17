package com.xiaoxin.projectinterface.common;

import lombok.Getter;

/**
 * 响应码枚举
 */
@Getter
public enum ResultCode {

    /* 通用状态码 成功与失败 */
    SUCCESS(100, "操作成功"),
    FAILED(-100, "操作失败"),

    /* 参数错误  101 - 199*/
    PARAM_INVALID(101,"参数无效"),
    PARAM_BLANK(102,"参数为空"),
    PARAM_ERROR(103, "参数错误"),

    /* 用户错误 201 - 299*/
    USER_LOGIN_ERROR(201,"登陆失败"),
    USER_REG_ERROR(202,"注册错误"),

    /* 查询数据错误 301 - 399*/
    DATA_EXISTED(301,"数据已存在"),
    DATA_EMPTY(302,"查询数据为空"),
    DATA_ERROR(303,"查询失败"),

    /* 功能错误 400 - 499 */
    IDENTIFY_ERROR(400,"识别错误"),

    /* 未知错误 500 */
    ERROR(500, "未知错误");

    private Integer code;
    private String msg;

    ResultCode(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

}

