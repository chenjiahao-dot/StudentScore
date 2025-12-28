package org.example.Demo.config;

import com.example.Token.TokenInterceptor;
import com.example.Util.JwtUtil;
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