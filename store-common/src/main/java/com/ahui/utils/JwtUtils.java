package com.ahui.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Date;
import java.util.List;

/**
 * @ClassName JWT工具类$
 * @Author pengzhaohui
 * @Description
 * @Date $ $
 **/
@Slf4j
@Component
public class JwtUtils {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private Long expiration;

    // 生成安全的密钥
    private SecretKey getSigningKey() {
        try {
            String decodedSecret = new String(Base64.getDecoder().decode(secret), StandardCharsets.UTF_8);
            if (decodedSecret.length() < 32) {
                throw new IllegalArgumentException("解码后的密钥长度必须≥32字符");
            }
            return Keys.hmacShaKeyFor(decodedSecret.getBytes(StandardCharsets.UTF_8));
        } catch (IllegalArgumentException e) {
            log.error("密钥配置错误: {}", e.getMessage());
            throw new JwtException("JWT密钥初始化失败", e);
        }
    }

    /**
     * 生成Token
     */
    public String generateToken(String username, List<String> authorities) {
        return Jwts.builder()
                .setSubject(username)
                .claim("auth", authorities)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expiration)) // 使用配置值
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    /**
     * 解析并验证Token
     */
    public Claims parseToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    /**
     * 验证Token有效性
     */
    public boolean validateToken(String token) {
        try {
            parseToken(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }

    public String getUsernameFromToken(String token) {
        return parseToken(token).getSubject();
    }
}
