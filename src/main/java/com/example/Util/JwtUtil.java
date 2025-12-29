package com.example.Util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtUtil {
    // 从配置文件读取密钥（至少32个字符，256位）
    @Value("${jwt.secret}")
    private  String secretKey;

    // 从配置文件读取Token有效期（毫秒，这里配置的是24小时）
    @Value("${jwt.expire}")
    private long tokenExpiration;

    // 生成签名密钥（适配HMAC-SHA256算法）
    private SecretKey getSignInKey() {
        byte[] keyBytes = secretKey.getBytes();
        return Keys.hmacShaKeyFor(keyBytes);
    }

    /**
     * 生成JWT Token
     * @param userId 存入Token的用户ID
     * @return 生成的Token
     */
    public String generateToken(Long userId,String userName) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("userId", userId);
        claims.put("userName", userName); // 存入用户名，key与提取时一致

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + tokenExpiration))
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

    /**
     * 从Token中提取用户ID
     */
    public Long extractUserId(String token) {
        // 替换Claims::getSubject为读取"userId"的claim
        return extractClaim(token, claims -> claims.get("userId", Long.class));
    }

    /**
     * 验证Token是否有效（用户ID匹配+未过期）
     */
    public boolean isTokenValid(String token, Long userId) {
        final Long extractedUserId = extractUserId(token);
        // 校验：extractedUserId非空 + 与传入的userId一致 + Token未过期
        return extractedUserId != null && extractedUserId.equals(userId) && !isTokenExpired(token);
    }

    // 提取Token中的指定声明
    private <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    // 解析Token获取所有声明
    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    // 检查Token是否过期
    private boolean isTokenExpired(String token) {
        return extractClaim(token, Claims::getExpiration).before(new Date());
    }
    /**
     * 解析Token（拦截器中调用）
     * @param token 前端传入的JWT Token
     * @return 解析出的用户ID
     * @throws Exception Token无效/过期时会抛出异常
     */
    /**
     * 解析Token（非static方法）
     */
    public Long parseToken(String token) throws Exception {
        Claims claims = Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token)
                .getBody();
        return Long.parseLong(claims.getSubject());
    }
    /**
     * 解析token，获取当前用户的ID（核心方法）
     * @param token 前端传递的JWT token
     * @return 当前用户的ID
     * @throws IllegalArgumentException token为空或无效
     */
    public  Long getUserIdFromToken(String token) {
        SecretKey key = Keys.hmacShaKeyFor(secretKey.getBytes());
        // 解析token，获取载荷
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(key) // 设置签名密钥
                .build()
                .parseClaimsJws(token)
                .getBody();
        // 从载荷中获取用户ID
        return claims.get("userId", Long.class);
    }

    /**
     * 从Token中提取用户名
     */
    public String getUserNameFromToken(String token) {
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(secretKey)
                    .parseClaimsJws(token)
                    .getBody();
            return claims.get("userName", String.class); // 提取用户名
        } catch (Exception e) {
            return null; // Token无效/过期返回null
        }
    }
}