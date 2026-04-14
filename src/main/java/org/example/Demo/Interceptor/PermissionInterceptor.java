package org.example.Demo.Interceptor;

import com.alibaba.fastjson.JSON;
import com.common.Result.Result;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.checkerframework.checker.units.qual.A;
import org.example.Demo.entity.User;
import org.example.Demo.entity.Users;
import org.example.Demo.mapper.PermissionMapper;
import org.example.Demo.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.List;

@Component
public class PermissionInterceptor implements HandlerInterceptor {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private PermissionMapper permissionMapper;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("token");
        if (token == null || token.trim().isEmpty()) {
            response.setStatus(401);
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write("{\"msg\":\"未登录，请先登录\"}");
            return false;
        }

        String userIdStr = stringRedisTemplate.opsForValue().get("login:token:" + token);
        if (userIdStr == null) {
            response.setStatus(401);
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write("{\"msg\":\"登录已过期\"}");
            return false;
        }

        Long userId = Long.parseLong(userIdStr);
        Users user = userMapper.selectById(userId);
        if (user == null) {
            response.setStatus(401);
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write("{\"msg\":\"用户不存在\"}");
            return false;
        }

        // 管理员直接放行
        if ("ADMIN".equals(user.getUserTypeEnum())) {
            return true;
        }

        // 获取当前请求路径
        String requestPath = request.getRequestURI();

        // 查询用户权限（SQL已做不区分大小写匹配）
        List<String> permissions = permissionMapper.getPermissionsByUserId(userId);

        // 权限匹配（前缀+精确都支持）
        boolean hasPermission = false;
        for (String path : permissions) {
            if (requestPath.startsWith(path) || requestPath.equals(path)) {
                hasPermission = true;
                break;
            }
        }

        if (!hasPermission) {
            response.setStatus(403);
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write("{\"msg\":\"无权限访问该接口\"}");
            return false;
        }

        return true;
    }
}