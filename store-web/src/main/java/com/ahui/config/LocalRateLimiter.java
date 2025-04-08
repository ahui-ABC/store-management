package com.ahui.config;

import com.google.common.util.concurrent.RateLimiter;
import org.springframework.stereotype.Component;

/**
 * @ClassName 限流工具类$
 * @Author pengzhaohui
 * @Description
 * @Date $ $
 **/
@Component
public class LocalRateLimiter {
    // 每秒允许5个请求
    private final RateLimiter rateLimiter = RateLimiter.create(5.0);

    public boolean tryAcquire() {
        return rateLimiter.tryAcquire();
    }
}
