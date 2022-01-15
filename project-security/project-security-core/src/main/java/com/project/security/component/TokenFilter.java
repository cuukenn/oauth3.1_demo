package com.project.security.component;

import cn.hutool.core.util.StrUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * 令牌过滤器:将请求头的令牌提取处理并进行处理
 *
 * @author changgg
 */
@RequiredArgsConstructor
@Slf4j
public class TokenFilter extends GenericFilterBean {
    private final TokenProvider tokenProvider;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        try {
            String token = tokenProvider.getToken((HttpServletRequest) servletRequest);
            if (StrUtil.isNotBlank(token)) {
                Authentication authentication = tokenProvider.getAuthentication(token);
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        } catch (RuntimeException exception) {
            log.error("令牌转换失败,msg:[{}]", exception.getMessage());
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
