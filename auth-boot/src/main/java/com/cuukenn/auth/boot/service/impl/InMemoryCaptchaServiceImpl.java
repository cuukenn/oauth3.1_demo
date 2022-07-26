package com.cuukenn.auth.boot.service.impl;

import cn.hutool.cache.Cache;
import cn.hutool.cache.CacheUtil;
import cn.hutool.core.util.IdUtil;
import com.cuukenn.auth.boot.service.ICaptchaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * 内存验证码存储器
 *
 * @author changgg
 */
@Service
@RequiredArgsConstructor
public class InMemoryCaptchaServiceImpl implements ICaptchaService {
    private final Cache<String, String> cache = CacheUtil.newFIFOCache(1000, 60 * 1000);

    @Override
    public String removeIfAbstract(String id) {
        String data = cache.get(id);
        if (data != null) {
            cache.remove(id);
        }
        return data;
    }

    @Override
    public String save(String code) {
        String id = IdUtil.fastSimpleUUID();
        cache.put(id, code);
        return id;
    }
}
