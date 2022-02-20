package com.project.core.util;

import cn.hutool.core.codec.Base64;
import com.alibaba.fastjson.JSON;
import com.project.core.constant.SecurityConstant;
import com.project.core.pojo.JwtPayloadDTO;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Objects;

/**
 * @author changgg
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class SecurityUtil {

    /**
     * 获取当前请求上下文的用户信息
     *
     * @return 用户信息
     */
    public static JwtPayloadDTO getCurrentUser() {
        String payload = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes()))
                .getRequest().getHeader(SecurityConstant.JWT_PAYLOAD_KEY);
        return JSON.toJavaObject(JSON.parseObject(Base64.decodeStr(payload)), JwtPayloadDTO.class);
    }
}
