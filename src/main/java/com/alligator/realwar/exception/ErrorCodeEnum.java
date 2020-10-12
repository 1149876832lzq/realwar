package com.alligator.realwar.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 错误枚举类
 */
@Getter
@AllArgsConstructor
public enum ErrorCodeEnum {

    //0**** 成功
    SUCCESS("0000","操作成功"),

    //1**** 参数异常
    PARAM_ERROE("1001","参数异常"),
    PARAM_NULL("1002","参数为空"),
    PARAM_FORMAT_ERROE("1003","参数格式不正确"),
    PARAM_VALUE_ERROE("1004","参数值不正确"),

    //2** 系统异常
    SYSTEM_ERROE("2001","服务异常"),
    UNKNOWN_ERROE("2002","未知异常"),

    //3****业务异常
    XXX("3001","业务异常"),
    INSERT_FAILURE("3002","新增失败"),
    UPDATE_FAILURE("3002","更新失败"),
    DELETE_FAILURE("3002","删除失败"),
    ;

    /**
     * 错误编码
     */
    private String code;

    /**
     * 错误描述
     */
    private String message;
}
