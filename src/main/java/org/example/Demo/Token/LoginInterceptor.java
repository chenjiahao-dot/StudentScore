package org.example.Demo.Token;

import com.common.Context.BaseContext;
import com.common.Util.JwtUtil;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

// 1. 定义拦截器
@Component
public class LoginInterceptor implements HandlerInterceptor {
    @Autowired
    private JwtUtil jwtUtil;
    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 1. 从请求头获取token
        String token = request.getHeader("token");
        if (token == null || token.isEmpty()) {
            response.setStatus(401);
            response.getWriter().write("未登录，请先登录");
            return false;
        }


        String userIdStr = stringRedisTemplate.opsForValue().get("login:token:" + token);
        if (userIdStr == null) {
            response.setStatus(401);
            response.getWriter().write("登录已过期，请重新登录");
            return false;
        }

        // 3. 解析用户ID，存入线程本地变量（原有逻辑保留）
        Long userId = jwtUtil.parseToken(token);
        BaseContext.setCurrentId(userId);

        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        // 清除线程本地变量，避免内存泄漏
        BaseContext.removeCurrentId();
    }
}