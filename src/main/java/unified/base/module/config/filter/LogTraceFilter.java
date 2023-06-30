package unified.base.module.config.filter;

import org.slf4j.MDC;
import unified.base.module.util.MD5Util;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @version 1.0.0
 * @Date: 2023/6/5 10:58
 * @Copyright (C) ZhuYouBin
 * @Description: log4j2日志过滤器，统一添加请求traceId，并且返回traceId给前端header头部
 */
public class LogTraceFilter implements Filter {
    private static final String TRACE_ID = "traceId";

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        // 生成traceId
        String traceId = MD5Util.getUUID();
        // 将生成的traceId放入到MDC里面
        MDC.put(TRACE_ID, traceId);
        // 将生成的traceId响应给前端header里面
        response.addHeader(TRACE_ID, traceId);
        // 继续执行过滤器
        chain.doFilter(request, response);
        // 过滤器执行结束之后，记得删除traceId，以免内存溢出
        MDC.remove(TRACE_ID);
    }

}
