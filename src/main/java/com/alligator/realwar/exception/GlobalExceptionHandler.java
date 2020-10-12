package com.alligator.realwar.exception;

import com.alligator.realwar.domain.common.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 全局异常处理捕获器
 */
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    /**
     * 拦截业务类异常
     * @param e
     * @return
     */
    @ResponseBody
    @ExceptionHandler(value = BusinessException.class)
    public ResponseResult businessExceptionHandle(BusinessException e) {

        log.error("捕捉到业务类异常：", e);
        return ResponseResult.failure(e.getCode(),e.getMessage());
    }

    /**
     * 拦截运行时异常
     * @param e
     */
    @ResponseBody
    @ExceptionHandler(value = RuntimeException.class)
    public ResponseResult runtimeExcetionHandle(RuntimeException e) {

        //仅仅捕获到还是会往上抛，需要做系统处理
        log.error("捕捉到运行时异常：", e);
        return ResponseResult.failure(ErrorCodeEnum.UNKNOWN_ERROE.getCode(),e.getMessage());
    }

    @ResponseBody
    @ExceptionHandler(value = Throwable.class)
    public ResponseResult throwableHandle(Throwable th) {

        log.error("捕捉到Throeable异常:", th);
        return ResponseResult.failure(ErrorCodeEnum.SYSTEM_ERROE.getCode(),th.getMessage());
    }
}
