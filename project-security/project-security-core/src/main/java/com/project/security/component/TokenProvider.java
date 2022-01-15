package com.project.security.component;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.KeyUtil;
import cn.hutool.jwt.JWT;
import cn.hutool.jwt.JWTUtil;
import cn.hutool.jwt.signers.AlgorithmUtil;
import cn.hutool.jwt.signers.JWTSigner;
import cn.hutool.jwt.signers.JWTSignerUtil;
import com.project.common.exception.BizException;
import com.project.security.config.JwtProperties;
import com.project.security.pojo.TokenPair;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotNull;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

/**
 * @author changgg
 */
@Slf4j
@RequiredArgsConstructor
public class TokenProvider {
    private static final String AUTHENTICATOR_KEY = "authenticator";
    private static final String AUTHORITIES_KEY = "authorities";
    private final JWTSigner tokenSigner = JWTSignerUtil.hs256(KeyUtil.generateKey(AlgorithmUtil.getAlgorithm("HS512")).getEncoded());
    private final JwtProperties jwtProperties;

    /**
     * 创建令牌对
     *
     * @param authentication 认证信息
     * @return 令牌对
     */
    public TokenPair createTokenPair(Authentication authentication) {
        return new TokenPair(wrap(createToken(authentication)), createRefreshToken());
    }

    /**
     * 创建Token，
     *
     * @param authentication 认证信息
     * @return token
     */
    private String createToken(Authentication authentication) {
        LocalDateTime now = LocalDateTime.now();
        Date issuedAt = Date.from(now.toInstant(ZoneOffset.of("+8")));
        Date expireAt = Date.from(now.minus(jwtProperties.getTokenInvalidateInMillSeconds(), ChronoUnit.MILLIS).toInstant(ZoneOffset.of("+8")));
        return JWT.create()
                .setCharset(StandardCharsets.UTF_8)
                .setJWTId(IdUtil.simpleUUID())
                .setIssuedAt(issuedAt)
                .setExpiresAt(expireAt)
                .setNotBefore(issuedAt)
                .setPayload(AUTHENTICATOR_KEY, authentication.getName())
                .setPayload(AUTHORITIES_KEY, authentication.getAuthorities())
                .setSigner(tokenSigner).sign();
    }

    /**
     * 创建refresh Token，
     *
     * @return token
     */
    private String createRefreshToken() {
        return IdUtil.simpleUUID();
    }

    /**
     * 依据Token 获取鉴权信息
     *
     * @param token 令牌
     * @return 认证信息
     */
    @SuppressWarnings("unchecked")
    public Authentication getAuthentication(String token) {
        return getAuthentication(token, true);
    }

    /**
     * 依据Token 获取鉴权信息
     *
     * @param token 令牌
     * @return 认证信息
     */
    @SuppressWarnings("unchecked")
    private Authentication getAuthentication(String token, boolean validate) {
        JWT jwt = JWTUtil.parseToken(token);
        if (validate) {
            Assert.isTrue(jwt.verify(tokenSigner), () -> new BizException("令牌不合法"));
            Assert.isTrue(jwt.validate(30L), () -> new BizException("令牌失效"));
        }
        String username = (String) jwt.getPayload(AUTHENTICATOR_KEY);
        Collection<? extends GrantedAuthority> authorities = (Collection<? extends GrantedAuthority>) jwt.getPayload(AUTHORITIES_KEY);
        User principal = new User(username, "******", authorities);
        return new UsernamePasswordAuthenticationToken(principal, token, new ArrayList<>());
    }

    /**
     * 从请求中获取登陆信息
     *
     * @param request 请求
     * @return 令牌
     */
    public String getToken(HttpServletRequest request) {
        final String requestHeader = request.getHeader(jwtProperties.getHeader());
        if (requestHeader != null && requestHeader.startsWith(jwtProperties.getHeaderPrefix())) {
            return requestHeader.substring(jwtProperties.getHeaderPrefix().length());
        }
        return null;
    }

    /**
     * 使用刷新令牌获取新的token
     *
     * @param tokenPair token
     * @return 新的令牌对
     */
    public TokenPair refreshToken(@NotNull TokenPair tokenPair) {
        Authentication authentication = getAuthentication(tokenPair.getToken(), false);
        String refreshToken = tokenPair.getRefreshToken();
        return new TokenPair(wrap(createToken(authentication)), refreshToken);
    }

    /**
     * 添加额外信息
     *
     * @param token 令牌
     * @return 被包装的令牌
     */
    private String wrap(String token) {
        if (StrUtil.isBlank(token)) {
            return null;
        }
        return jwtProperties.getHeaderPrefix() + token;
    }
}
