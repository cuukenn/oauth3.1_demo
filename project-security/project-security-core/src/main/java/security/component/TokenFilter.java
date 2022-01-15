package com.project.security.component;

import cn.hutool.core.util.StrUtil;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @author changgg
 */
public class TokenFilter extends GenericFilterBean {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        String token = null;
        // 对于Token为空的不需要去查缓存
        if (StrUtil.isNotBlank(token)) {
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
