package org.example.Demo.config;

import org.example.Demo.Token.TokenInterceptor;
import com.common.Util.JwtUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {
    @Bean // 手动将JwtUtil注册到Spring容器
    public JwtUtil jwtUtil() {
        return new JwtUtil(); // 若JwtUtil有构造参数，需传入依赖
    }
    @Bean
    public TokenInterceptor tokenInterceptor(JwtUtil jwtUtil) {
        return new TokenInterceptor(jwtUtil);
    }
}