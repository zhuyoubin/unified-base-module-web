package unified.base.module.util;

import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.util.Objects;

/**
 * @version 1.0.0
 * @Date: 2023/6/19 11:43
 * @Author ZhuYouBin
 * @Description: IP、地址工具类
 */
public class IpAddressUtil {
    private static final String UNKNOWN_IP = "unknown";
    /**
     * 根据IP地址获取省份的位置接口
     */
    private static final String WHOIS_URL = "https://whois.pconline.com.cn/ipJson.jsp";

    /**
     * 获取请求客户端的真实IP地址
     */
    public static String getClientIp(HttpServletRequest request) {
        if (request == null) return UNKNOWN_IP;
        String ip = getIp(request, "x-forwarded-for", null);
        ip = getIp(request, "Proxy-Client-IP", ip);
        ip = getIp(request, "X-Forwarded-For", ip);
        ip = getIp(request, "WL-Proxy-Client-IP", ip);
        ip = getIp(request, "X-Real-IP", ip);
        if (ip == null || ip.length() == 0 || Objects.equals(UNKNOWN_IP, ip)) {
            ip = request.getRemoteAddr();
        }
        try {
            if (Objects.equals("0:0:0:0:0:0:0:1", ip) || Objects.equals("127.0.0.1", ip)) {
                return InetAddress.getLocalHost().getHostAddress();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ip;
    }

    /**
     * 获取IP地址对应的位置信息
     * @param ip
     * @return 例如：xxx省xxx县
     */
    public static String getRealAddressByIp(String ip) {
        return getAddress(WHOIS_URL + "?ip=" + ip + "&json=true");
    }

    //*********************************** private ***************************************//

    private static String getIp(HttpServletRequest request, String header, String ip) {
        if (ip == null || ip.length() == 0 || Objects.equals(UNKNOWN_IP, ip)) {
            ip = request.getHeader(header);
        }
        return ip;
    }

    private static String getAddress(String url) {
        try {
            HttpRequest request = HttpUtil.createGet(url);
            HttpResponse response = request.execute();
            String body = response.body();
            if (StrUtil.isNotBlank(body)) {
                JSONObject json = JSONUtil.parseObj(body);
                return String.format("%s%s", json.get("pro"), json.get("city"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
