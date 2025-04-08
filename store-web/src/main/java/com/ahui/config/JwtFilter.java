package com.ahui.config;

import com.ahui.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @ClassName $
 * @Author pengzhaohui
 * @Description
 * @Date $ $
 **/
/**
 * JWT认证过滤器
 */
@Component
public class JwtFilter extends OncePerRequestFilter {

    private static final String AUTHORIZATION_HEADER = "Authorization";
    private static final String TOKEN_PREFIX = "Bearer ";

    @Autowired
    private JwtUtils jwtUtils;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        try {
            // 1. 从请求头获取Token
            String jwt = resolveToken(request);

            if (StringUtils.hasText(jwt)) {
                // 2. 验证并解析Token
                Claims claims = jwtUtils.parseToken(jwt);

                // 3. 构建Authentication对象
                Authentication authentication = createAuthentication(claims);

                // 4. 存入SecurityContext
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }

            // 5. 继续过滤器链
            filterChain.doFilter(request, response);
        } catch (ExpiredJwtException e) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("Token已过期");
        } catch (JwtException | IllegalArgumentException e) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("无效的Token");
        }
    }

    /**
     * 从请求头中解析Token
     */
    private String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader(AUTHORIZATION_HEADER);
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith(TOKEN_PREFIX)) {
            return bearerToken.substring(TOKEN_PREFIX.length());
        }
        return null;
    }

    /**
     * 根据Claims创建Authentication对象
     */
    private Authentication createAuthentication(Claims claims) {
        String username = claims.getSubject();

        // 合并角色和权限
        List<String> roles = claims.get("roles", List.class);
        List<String> perms = claims.get("perms", List.class);

        List<GrantedAuthority> authorities = new ArrayList<>();
        if (roles != null) {
            authorities.addAll(roles.stream()
                    .map(SimpleGrantedAuthority::new)
                    .collect(Collectors.toList()));
        }
        if (perms != null) {
            authorities.addAll(perms.stream()
                    .map(SimpleGrantedAuthority::new)
                    .collect(Collectors.toList()));
        }

        return new UsernamePasswordAuthenticationToken(
                username, null, authorities
        );
    }
}
