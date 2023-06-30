package unified.base.module.auto.config;

import cn.hutool.core.util.StrUtil;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdScalarDeserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import unified.base.module.auto.property.UnifiedBaseModuleProperties;
import unified.base.module.config.filter.LogTraceFilter;
import unified.base.module.exp.GlobalExceptionHandler;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;

/**
 * @version 1.0.0
 * @Date: 2023/6/2 16:49
 * @Copyright (C) ZhuYouBin
 * @Description: 自动配置
 */
@Configuration(proxyBeanMethods = false)
@EnableConfigurationProperties({ UnifiedBaseModuleProperties.class })
public class UnifiedBaseModuleAutoConfiguration {
    private static final Logger logger = LoggerFactory.getLogger(UnifiedBaseModuleAutoConfiguration.class);

    /**
     * 全局异常处理
     */
    @Bean
    @ConditionalOnMissingBean
    public GlobalExceptionHandler globalExceptionHandler() {
        logger.info("自动配置[GlobalExceptionHandler]全局异常处理器.");
        return new GlobalExceptionHandler();
    }

    /**
     * jackson配置
     */
    @Bean
    public Jackson2ObjectMapperBuilderCustomizer jsonCustomizer() {
        logger.info("自动配置[Jackson]。");
        return new Jackson2ObjectMapperBuilderCustomizer() {
            @Override
            public void customize(Jackson2ObjectMapperBuilder builder) {
                // 设置序列化特征
                builder.serializationInclusion(JsonInclude.Include.NON_NULL)
                        // 反序列的时候，针对String字符串类型，去除前后空白字符
                        .deserializerByType(String.class, new StdScalarDeserializer<String>(String.class) {
                            @Override
                            public String deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
                                return StrUtil.trim(p.getValueAsString());
                            }
                        })
                        .timeZone("Asia/Shanghai")
                        .simpleDateFormat("yyyy-MM-dd HH:mm:ss");
            }
        };
    }

    /**
     * 自动配置自定义的日志过滤器
     */
    @Bean
    public FilterRegistrationBean<LogTraceFilter> logTraceFilter() {
        FilterRegistrationBean<LogTraceFilter> reg = new FilterRegistrationBean<>();
        reg.setFilter(new LogTraceFilter());
        reg.setName("LogTraceFilter");
        reg.setUrlPatterns(Collections.singleton("/*"));
        reg.setOrder(Ordered.LOWEST_PRECEDENCE);
        logger.info("自动配置[LogTraceFilter]日志过滤器。");
        return reg;
    }

    /**
     * 自动配置CORS跨域过滤器
     */
    @Bean
    public FilterRegistrationBean<CorsFilter> corsFilter() {
        // 创建 Cors 配置对象
        CorsConfiguration config = new CorsConfiguration();
        config.addAllowedOrigin("*"); // 设置允许哪些跨域
        config.addAllowedHeader("*"); // 设置允许的消息头
        config.addAllowedMethod("*"); // 设置允许的响应头
        config.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE")); // 设置允许的跨域请求方式
        config.setAllowCredentials(true); // 设置允许cookie跨域
        config.setMaxAge(3600L); // 设置 OPTIONS 请求的缓存时间
        // 创建url配置资源
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config); // 注册 CORS 跨域配置
        CorsFilter corsFilter = new CorsFilter(source); // 创建 CorsFilter 过滤器
        // 创建注册过滤器的Bean对象
        FilterRegistrationBean<CorsFilter> reg = new FilterRegistrationBean<>(corsFilter);
        reg.setOrder(Ordered.HIGHEST_PRECEDENCE); // 设置 CorsFilter 过滤器最先执行
        logger.info("自动配置[CorsFilter]CORS跨域过滤器。");
        return reg;
    }
}
