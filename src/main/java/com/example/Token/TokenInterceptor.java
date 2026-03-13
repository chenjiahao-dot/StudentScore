package com.example.Token;

import com.example.Context.BaseContext;
import com.example.Util.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.io.PrintWriter;

@Component
public class TokenInterceptor implements HandlerInterceptor {

    // 注入JWT工具类
    @Autowired
    private JwtUtil jwtUtil;

    public TokenInterceptor(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    /**
     * 请求处理前执行：校验Token
     * @return true：放行，false：拦截
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 1. 排除公开接口（如登录、注册，根据你的实际接口路径调整）

        String requestURI = request.getRequestURI();
        if (requestURI.contains("/user/login") || requestURI.contains("/user/register")) {
            return true; // 公开接口直接放行
        }

        // 2. 从请求头获取Token（约定格式：Authorization: Bearer {token}）
        String token = request.getHeader("token");
        if (token == null ) {
            // 无Token或格式错误，返回401未授权
            return handleUnauthorized(response, "请先登录，或传入正确格式的Token");
        }

        // 去除Bearer前缀，得到纯Token
        token = token.replace("Bearer ", "");

        // 3. 校验Token有效性（捕获解析异常：如签名错误、Token过期、格式错误）
            // 从Token中提取用户ID（解析过程会自动校验签名和格式）
            Long userId = jwtUtil.extractUserId(token);
            // 额外校验：Token是否与用户匹配（这里简化处理，可根据业务扩展，比如从数据库查询用户）
            if (!jwtUtil.isTokenValid(token, userId)) {
                return handleUnauthorized(response, "Token无效");
            }

            // 可选：将用户ID存入请求属性，供后续接口使用
            BaseContext.setCurrentId(userId);
            request.setAttribute("userId", userId);
        // 4. Token有效，放行请求
        return true;
    }

    /**
     * 处理未授权的响应：返回JSON格式的提示信息
     */
    private boolean handleUnauthorized(HttpServletResponse response, String message) throws Exception {
        response.setContentType("application/json;charset=UTF-8");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED); // 401状态码
        PrintWriter out = response.getWriter();
        // 返回统一的JSON响应
        out.write("{\"code\":401,\"message\":\"" + message + "\"}");
        out.flush();
        out.close();
        return false; // 拦截请求，不继续处理
    }
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        BaseContext.removeCurrentId();
    }
}