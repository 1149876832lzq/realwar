package com.alligator.realwar.util;

import org.springframework.util.CollectionUtils;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

/**
 * 校验工具类 手动校验用到
 */
public class ValidatorUtils {

    //全局校验器
    private static Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    /**
     * 参数校验
     * @param object
     * @param groups
     * @param <T>
     */
    public static <T> void validate(T object,Class... groups) {

        Set<ConstraintViolation<T>> validate = validator.validate(object, groups);

        //如果校验结果不为空
        if(!CollectionUtils.isEmpty(validate)) {
            //拼接异常信息
            StringBuilder exceptionMessage = new StringBuilder();
            validate.forEach(tConstraintViolation -> {
                exceptionMessage.append(tConstraintViolation.getMessage());
            });

            throw new RuntimeException(exceptionMessage.toString());
        }
    }
}
