package com.ahui.utils;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;

/**
 * @ClassName JWT工具类$
 * @Author pengzhaohui
 * @Description
 * @Date $ $
 **/
@Slf4j
public class JwtUtils {
    private static final String SECRET_KEY = "your-256-bit-secret"; // 替换为实际密钥
    private static final long EXPIRATION_TIME = 3600 * 1000; // 1小时

    public static String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }

    public static String getUsernameFromToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(SECRET_KEY)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public static boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(SECRET_KEY).build().parseClaimsJws(token);
            return true;
        } catch (ExpiredJwtException e) {
            log.error("Token已过期", e);
        } catch (MalformedJwtException e) {
            log.error("Token格式错误", e);
        }
        return false;
    }
}
