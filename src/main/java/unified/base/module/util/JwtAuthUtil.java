package unified.base.module.util;

import cn.hutool.core.util.StrUtil;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

/**
 * @version 1.0.0
 * @Date: 2023/6/17 10:58
 * @Copyright (C) ZhuYouBin
 * @Description: JSON Web Token 工具类（JWT）
 */
public class JwtAuthUtil {
    private static final Logger logger = LoggerFactory.getLogger(JwtAuthUtil.class);

    /**
     * 密钥字符串
     */
    private static final String SECRET = "JSON-Web-Token";
    /**
     * JWT 发行人
     */
    private static final String ISSUER = "JSON-Web-Token";
    /**
     * 用户名称
     */
    private static final String USERNAME = "username";
    /**
     * 加签、加密算法
     */
    private static final Algorithm algorithm = Algorithm.HMAC512(SECRET);

    /**
     * 生成JWT的Token
     * @param username 用户名称
     * @return 返回生成的Token
     */
    public static String generateToken(String username) {
        Map<String, String> claims = new HashMap<>();
        claims.put(USERNAME, username);
        return generateToken(claims);
    }

    /**
     * 生成JWT的Token
     * @param claims 消息体的声明信息，例如：用户名称、用户ID等等
     * @return 返回生成的Token
     */
    public static String generateToken(Map<String, String> claims) {
        Map<String, Object> headerMap = new HashMap<>();
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.HOUR, 6);
        // 生成 Token
        final JWTCreator.Builder builder = JWT.create()
                .withHeader(headerMap) // JWT的 Header 消息头
                .withIssuer(ISSUER) // JWT 的发行人
                .withIssuedAt(new Date()) // JWT 生成时间
                .withExpiresAt(calendar.getTime());// 设置 JWT 过期时间
        // 设置消息体的声明信息（Payload 载荷信息）
        if (!Objects.isNull(claims)) {
            claims.forEach(builder::withClaim);
        }
        return builder.sign(algorithm);
    }

    /**
     * 校验 Token 是否合法
     * <p>1、是否合法的 JWT 字符串</p>
     * <p>2、JWT 字符串是否过期</p>
     */
    public static boolean isValidToken(String token) {
        try {
            if (StrUtil.isBlank(token)) {
                return false;
            }
            // 解析 token
            DecodedJWT decode = JWT.decode(token);
            // 判断是否过期
            return new Date().before(decode.getExpiresAt());
        } catch (Exception e) {
            logger.error("异常信息:", e);
        }
        return false;
    }

    /**
     * 获取用户名称
     */
    public static String getUsername(String token) {
        try {
            if (StrUtil.isBlank(token)) {
                return null;
            }
            // 解析 token
            DecodedJWT decode = JWT.decode(token);
            // 判断是否过期
            Claim claim = decode.getClaim(USERNAME);
            return claim.asString();
        } catch (Exception e) {
            logger.error("异常信息:", e);
        }
        return null;
    }
}