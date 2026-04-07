package org.example.Demo.config;

import org.example.Demo.Token.TokenInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private TokenInterceptor tokenInterceptor;
    @Value("${file.upload-path}")
    private String uploadPath;

    @Value("${file.access-path}")
    private String accessPath;
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler(accessPath+"**")
                .addResourceLocations("file:"+uploadPath);
    }
    /**
     * 注册拦截器，配置拦截/排除规则
     */

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(tokenInterceptor)
                .addPathPatterns("/**") // 拦截所有请求
                .excludePathPatterns( // 排除不需要拦截的接口
                        "/user/Login", // 登录接口
                        "/user/register", // 注册接口
                        // 可选：排除接口文档相关路径（如Knife4j/Swagger）
                        "/doc.html",
                        "/swagger-ui/**",
                        "/v3/api-docs/**"
                );

    }


}