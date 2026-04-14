package org.example.Demo.config;

import org.example.Demo.Interceptor.PermissionInterceptor;
import org.example.Demo.Interceptor.TokenInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Autowired
    private PermissionInterceptor permissionInterceptor; // 注入权限拦截器
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
                .addPathPatterns("/**")
                .excludePathPatterns(
                        "/user/Login",
                        "/user/register",
                        "/doc.html",
                        "/swagger-ui/**",
                        "/v3/api-docs/**"
                );
        // 2. 再注册权限拦截器（登录通过后，再校验权限）
        registry.addInterceptor(permissionInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns(
                        "/user/Login",
                        "/user/register",
                        "/doc.html",
                        "/swagger-ui/**",
                        "/v3/api-docs/**"
                );
    }



}