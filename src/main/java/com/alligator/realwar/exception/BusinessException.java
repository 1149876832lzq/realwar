package com.alligator.realwar.exception;

import com.sun.applet2.preloader.event.ErrorEvent;
import lombok.Getter;

/**
 * 业务类异常
 */
public class BusinessException extends RuntimeException {

    /**
     * 异常编号
     */
    @Getter
    private final String code;

    /**
     * 根据枚举构造业务类异常
     * @param error
     */
    public BusinessException(ErrorCodeEnum error) {
        super(error.getMessage());
        this.code = error.getCode();
    }

    /**
     * 自定义消息体构造业务类异常
     * @param error
     * @param message
     */
    public BusinessException(ErrorCodeEnum error,String message) {
        super(message);
        this.code = error.getCode();
    }

    /**
     * 根据异常构造业务类异常
     * @param error
     * @param cause
     */
    public BusinessException(ErrorCodeEnum error,Throwable cause) {
        super(cause);
        this.code = error.getCode();
    }
}
