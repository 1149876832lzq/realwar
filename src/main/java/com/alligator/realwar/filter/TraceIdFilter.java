package com.alligator.realwar.filter;


import org.slf4j.MDC;
import org.springframework.core.annotation.Order;
import org.springframework.util.StringUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;
import java.util.UUID;

@WebFilter(urlPatterns = "/*")
@Order(1)
public class TraceIdFilter implements Filter {

    /**
     * traceId常量
     */
    private static final String TRACE_ID = "traceId";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest,
                         ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {

        //尝试获取traceId
        String traceId = servletRequest.getParameter(TRACE_ID);

        //为空设置默认值
        if (StringUtils.isEmpty(traceId)) {
            traceId = UUID.randomUUID().toString();
        }

        //在MDC中放入traceId，MDC其实就是日志框架给我们提供的一个ThreadLocal对象,可以理解为一个
        //和当前线程绑定的map集合啊，这样的话就同一个请求都是同一个traceId进行请求的跟踪
        MDC.put(TRACE_ID, traceId);

        filterChain.doFilter(servletRequest,servletResponse);

    }

    @Override
    public void destroy() {

    }
}
