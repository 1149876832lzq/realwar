package com.alligator.realwar.domain.common;

import com.alligator.realwar.exception.ErrorCodeEnum;
import lombok.Data;
import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;

/**
 * 通用返回结果 数据模型
 */
@Data
public class ResponseResult<T> implements Serializable {

    private static final long serialVersionUID = -5830580322573740379L;

    //是否成功
    private Boolean success;

    //编码
    private String code;

    //描述信息
    private String message;

    //结果
    private T result;

    /**
     * 静态的成功方法
     * @param result
     * @param <T>
     * @return
     */
    public static <T> ResponseResult<T> success(T result) {
        ResponseResult<T> responseResult = new ResponseResult<>();

        responseResult.setSuccess(Boolean.TRUE);
        responseResult.setResult(result);

        return responseResult;
    }


    /**
     * 静态的失败方法
     * @param code
     * @param message
     * @param <T>
     * @return
     */
    public static <T> ResponseResult<T>  failure(String code, String message) {

        ResponseResult<T> responseResult = new ResponseResult<>();

        responseResult.setSuccess(Boolean.FALSE);
        responseResult.setCode(code);
        responseResult.setMessage(message);

        return responseResult;
    }

    /**
     * 枚举方法
     * @param codeEnum
     * @param <T>
     * @return
     */
    public static <T> ResponseResult<T> failure(ErrorCodeEnum codeEnum) {

        return failure(codeEnum.getCode(),codeEnum.getMessage());
    }
}
