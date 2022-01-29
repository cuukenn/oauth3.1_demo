package com.project.starter.component;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

/**
 * @author changgg
 */
@Slf4j
@RequiredArgsConstructor
public class RedisOpsService {
    private final RedisTemplate<Object, Object> objectRedisTemplate;
    private final StringRedisTemplate stringRedisTemplate;

    public void set(String key, String value) {
        stringRedisTemplate.opsForValue().set(key, value);
    }

    public void set(String key, String value, long timeout) {
        stringRedisTemplate.opsForValue().set(key, value, timeout);
    }

    public void set(String key, Object value) {
        objectRedisTemplate.opsForValue().set(key, value);
    }

    public void set(String key, Object value, long timeout) {
        objectRedisTemplate.opsForValue().set(key, value, timeout);
    }

    public String getStringValue(String key) {
        return stringRedisTemplate.opsForValue().get(key);
    }

    public Object getObjectValue(String key) {
        return objectRedisTemplate.opsForValue().get(key);
    }

    public boolean hasKey(String key) {
        return Boolean.TRUE.equals(objectRedisTemplate.hasKey(key));
    }

}
