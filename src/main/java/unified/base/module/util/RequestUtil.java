package unified.base.module.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;

import javax.servlet.http.HttpServletResponse;
import java.nio.charset.StandardCharsets;

/**
 * @version 1.0.0
 * @Date: 2023/6/30 9:24
 * @Author ZhuYouBin
 * @Description: 请求、响应工具类
 */
public class RequestUtil {
    private static final Logger logger = LoggerFactory.getLogger(RequestUtil.class);

    /**
     * 401未认证、未登录
     * @param response 响应对象
     * @param json 返回值
     */
    public static void resp_401(HttpServletResponse response, String json) {
        try {
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            response.setCharacterEncoding(StandardCharsets.UTF_8.toString());
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().println(json);
        } catch (Exception e) {
            logger.error("异常信息:", e);
        }
    }

    /**
     * 403已认证、没有访问权限
     * @param response 响应对象
     * @param json 返回值
     */
    public static void resp_403(HttpServletResponse response, String json) {
        try {
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            response.setCharacterEncoding(StandardCharsets.UTF_8.toString());
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            response.getWriter().println(json);
        } catch (Exception e) {
            logger.error("异常信息:", e);
        }
    }

}
