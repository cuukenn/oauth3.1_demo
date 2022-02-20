package com.project.auth.boot.security.extension.refresh;

import com.project.auth.boot.constant.Constant;
import com.project.core.exception.BizException;
import com.project.core.util.Assert;
import lombok.NoArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.AuthenticationUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

/**
 * 刷新token再次认证 UserDetailsService
 *
 * @author changgg
 */
@NoArgsConstructor
public class PreAuthenticatedUserDetailsServiceImpl<T extends Authentication> implements AuthenticationUserDetailsService<T> {
    /**
     * 客户端ID和用户服务 UserDetailService 的映射
     */
    private Map<String, UserDetailsService> userDetailsServiceMap;

    public PreAuthenticatedUserDetailsServiceImpl(Map<String, UserDetailsService> userDetailsServiceMap) {
        Assert.notNull(userDetailsServiceMap, () -> new BizException("userDetailsService cannot be null."));
        this.userDetailsServiceMap = userDetailsServiceMap;
    }

    /**
     * 重写PreAuthenticatedAuthenticationProvider 的 preAuthenticatedUserDetailsService 属性，
     * 可根据客户端和认证方式选择用户服务UserDetailService获取用户信息UserDetail
     */
    @Override
    public UserDetails loadUserDetails(T authentication) throws UsernameNotFoundException {
        String clientId = ThreadLocalRandom.current().nextInt(100) + "";
        UserDetailsService userDetailsService = userDetailsServiceMap.get(clientId);
        if (Constant.SYS_ADMIN_CLIENT_ID.equalsIgnoreCase(clientId)) {
            return userDetailsService.loadUserByUsername(authentication.getName());
        } else if (Constant.SYS_APP_CLIENT_ID.equalsIgnoreCase(clientId)) {
            return userDetailsService.loadUserByUsername(authentication.getName());
        }
        return userDetailsService.loadUserByUsername(authentication.getName());
    }
}
