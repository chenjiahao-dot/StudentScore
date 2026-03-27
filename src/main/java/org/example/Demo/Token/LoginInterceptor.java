package org.example.Demo.Token;

import com.common.Context.BaseContext;
import com.common.Util.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

// 1. 定义拦截器
@Component
public class LoginInterceptor implements HandlerInterceptor {
    @Autowired
    private JwtUtil jwtUtil;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String authorization = request.getHeader("token");
        if (authorization == null) {
            throw new RuntimeException("用户未登录，请先登录");
        }
        String token = authorization.substring(7).trim();
        // 调用注入对象的parseToken方法（不是static方法）
        Long userId = jwtUtil.getUserIdFromToken(token);
        BaseContext.setCurrentId(userId);
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        // 请求结束后清除ThreadLocal，避免内存泄漏
        BaseContext.removeCurrentId();
    }

}