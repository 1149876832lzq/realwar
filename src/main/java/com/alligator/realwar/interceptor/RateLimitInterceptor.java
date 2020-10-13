package com.alligator.realwar.interceptor;

import com.alligator.realwar.exception.BusinessException;
import com.alligator.realwar.exception.ErrorCodeEnum;
import com.google.common.util.concurrent.RateLimiter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 全局限流拦截器
 */
@Slf4j
@Component
public class RateLimitInterceptor implements HandlerInterceptor {

    //限流器实例 (QPS)限制为10,每秒最多产生10个令牌
    private static final RateLimiter ratelimiter = RateLimiter.create(10);

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {

        //尝试获取令牌
        if(!ratelimiter.tryAcquire()) {

            log.error("系统已经被限流");
            throw new BusinessException(ErrorCodeEnum.RATE_LIMIT_ERROE);
        }

        return true;
    }
}
