package com.project.security.service.impl;

import cn.hutool.cache.impl.CacheObj;
import cn.hutool.cache.impl.TimedCache;
import cn.hutool.core.date.LocalDateTimeUtil;
import cn.hutool.core.util.ReflectUtil;
import cn.hutool.extra.spring.SpringUtil;
import com.project.security.component.TokenProvider;
import com.project.security.config.JwtProperties;
import com.project.security.pojo.TokenPair;
import com.project.security.service.IOnlineUserService;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

/**
 * @author changgg
 */
@SuppressWarnings("SpringJavaAutowiredMembersInspection")
public class InMemoryOnLineUserServiceImpl implements IOnlineUserService {
    private final CustomerTimedCache cache = new CustomerTimedCache(60 * 60 * 1000);
    @Autowired
    @Setter
    private JwtProperties jwtProperties;
    @Autowired
    @Setter
    private TokenProvider tokenProvider;

    @Override
    public void save(TokenPair tokenPair, boolean rememberMe) {
        LocalDateTime now = LocalDateTime.now();
        long millSeconds = rememberMe ? jwtProperties.getRefreshTokenInvalidateRememberMeInMillSeconds() : jwtProperties.getRefreshTokenInvalidateInMillSeconds();
        LocalDateTime expireAt = now.minus(millSeconds, ChronoUnit.MILLIS).withHour(1).withMinute(30).withSecond(0);
        //选择一个当前时间后面n天的01:30:00作为刷新令牌失效时间
        if (!expireAt.isAfter(now)) {
            expireAt = expireAt.minus(1L, ChronoUnit.DAYS);
        }
        cache.put(tokenPair.getToken(), tokenPair.getRefreshToken(), LocalDateTimeUtil.between(now, expireAt).toMillis());
    }

    @Override
    public void refresh(TokenPair tokenPair) {
        Long timeout = cache.getTimeout(tokenPair.getToken());
        if (timeout != null) {
            cache.put(tokenPair.getToken(), tokenPair.getRefreshToken(), timeout);
        }
    }

    @Override
    public boolean validate(TokenPair tokenPair) {
        String refreshToken = cache.get(tokenPair.getToken());
        if (refreshToken == null) {
            return false;
        }
        return Objects.equals(refreshToken, tokenPair.getRefreshToken());
    }

    @Override
    public void logout() {
        String token = tokenProvider.getToken(SpringUtil.getBean(HttpServletRequest.class));
        if (token != null) {
            cache.remove(token);
        }
    }

    @Slf4j
    private static class CustomerTimedCache extends TimedCache<String, String> {

        private static final long serialVersionUID = 7221818028970209536L;

        CustomerTimedCache(long timeout) {
            super(timeout);
        }

        /**
         * 获取对应key的ttl
         *
         * @param key 令牌
         * @return ttl (0为无限)
         */
        public Long getTimeout(String key) {
            CacheObj<String, String> cacheItem = cacheMap.get(key);
            if (cacheItem == null) {
                return null;
            }
            Field ttl = ReflectUtil.getField(CacheObj.class, "ttl");
            ttl.setAccessible(true);
            try {
                long ttlValue = (long) ttl.get(cacheItem);
                return ttlValue == 0 ? Long.MAX_VALUE : ttlValue;
            } catch (IllegalAccessException e) {
                log.error("get ttl failed,token:[{}]", key, e);
                return null;
            }
        }
    }
}
