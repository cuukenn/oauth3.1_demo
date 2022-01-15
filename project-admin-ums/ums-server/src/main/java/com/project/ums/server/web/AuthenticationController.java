package com.project.ums.server.web;

import cn.hutool.core.lang.Assert;
import cn.hutool.crypto.digest.MD5;
import com.project.common.api.ApiResult;
import com.project.common.exception.BizException;
import com.project.common.util.CaptchaUtil;
import com.project.common.util.RegexUtil;
import com.project.security.annotation.GetAnonymousAccess;
import com.project.security.annotation.PostAnonymousAccess;
import com.project.security.component.TokenProvider;
import com.project.security.pojo.TokenPair;
import com.project.security.service.ICaptchaService;
import com.project.security.service.IOnlineUserService;
import com.project.ums.server.poj.AuthUserDTO;
import com.project.ums.server.poj.CaptchaDTO;
import com.project.ums.server.poj.PasswordAuthQuery;
import com.wf.captcha.base.Captcha;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.security.authentication.AccountStatusException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

/**
 * @author changgg
 */
@RestControllerAdvice
@RequiredArgsConstructor
@Slf4j
public class AuthenticationController {
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final ICaptchaService captchaService;
    private final IOnlineUserService onlineUserService;
    private final TokenProvider tokenProvider;

    /**
     * 用户登陆
     *
     * @param passwordAuthQuery 登陆DTO
     * @return 登陆结果
     */
    @PostMapping(value = "/login")
    @PostAnonymousAccess
    public ApiResult<AuthUserDTO> login(PasswordAuthQuery passwordAuthQuery) {
        ApiResult<AuthUserDTO> response;
        try {
            String exceptedCaptcha = captchaService.removeIfAbstract(passwordAuthQuery.getCaptchaId());
            if (exceptedCaptcha == null) {
                throw new BizException("验证码失效");
            }
            if (!Objects.equals(exceptedCaptcha, passwordAuthQuery.getCaptchaCode())) {
                throw new BizException("验证码错误");
            }
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                    passwordAuthQuery.getUsername(),
                    passwordAuthQuery.getPassword()
            );
            Authentication authenticate = authenticationManagerBuilder.getObject().authenticate(authentication);
            SecurityContextHolder.getContext().setAuthentication(authenticate);
            //创建令牌对、并保存令牌队
            TokenPair tokenPair = tokenProvider.createTokenPair(authentication);
            onlineUserService.save(tokenPair, passwordAuthQuery.isRememberMe());
            // 返回认证信息
            response = ApiResult.success(new AuthUserDTO(tokenPair.getToken(), tokenPair.getRefreshToken()));
        } catch (UsernameNotFoundException exception) {
            response = ApiResult.fail("用户名或密码错误");
            log.error("auth failed", exception);
        } catch (BadCredentialsException exception) {
            response = ApiResult.fail("认证失败");
            log.error("auth failed", exception);
        } catch (AccountStatusException exception) {
            response = ApiResult.fail("账号状态异常");
            log.error("auth failed", exception);
        } catch (AuthenticationException exception) {
            response = ApiResult.fail("登录失败");
            log.error("auth failed", exception);
        }
        return response;
    }

    /**
     * 用户登陆(跳过验证码,测试/开发环境有效)
     *
     * @param username 用户名
     * @param password 密码
     * @return 登陆结果
     */
    @PostMapping(value = "/fakeLogin")
    @PostAnonymousAccess
    @Profile({"dev", "test"})
    public ApiResult<AuthUserDTO> login(String username, String password) {
        String captchaCode = "only for test";
        String captchaId = captchaService.save(captchaCode);
        PasswordAuthQuery passwordAuthQuery = new PasswordAuthQuery();
        passwordAuthQuery.setUsername(username);
        passwordAuthQuery.setPassword(MD5.create().digestHex(password, StandardCharsets.UTF_8));
        passwordAuthQuery.setCaptchaId(captchaId);
        passwordAuthQuery.setCaptchaCode(captchaCode);
        return login(passwordAuthQuery);
    }

    /**
     * 使用刷新令牌重新获取token
     *
     * @param token        令牌
     * @param refreshToken 刷新令牌
     * @return 登陆结果
     */
    @PostMapping(value = "/refreshToken")
    @PostAnonymousAccess
    public ApiResult<AuthUserDTO> refreshToken(String token, String refreshToken) {
        TokenPair tokenPair = new TokenPair(token, refreshToken);
        Assert.isTrue(onlineUserService.validate(tokenPair), () -> new BizException("refreshToken失败,请重新登陆"));
        tokenPair = tokenProvider.refreshToken(tokenPair);
        onlineUserService.refresh(tokenPair);
        return ApiResult.success(new AuthUserDTO(tokenPair.getToken(), tokenPair.getRefreshToken()));
    }

    /**
     * 获取验证码
     *
     * @param response response
     * @return 验证码
     */
    @GetMapping(value = "/captchaCode")
    @GetAnonymousAccess
    public ApiResult<CaptchaDTO> getCaptchaCode(HttpServletResponse response) {
        return getCaptchaCode(130, 48, response);
    }

    /**
     * 获取验证码
     *
     * @param captchaWidth  验证码宽带
     * @param captchaHeight 验证码高度
     * @param response      response
     * @return 验证码
     */
    @GetMapping(value = "/captchaCode/{captchaWidth}/{captchaHeight}")
    @GetAnonymousAccess
    public ApiResult<CaptchaDTO> getCaptchaCode(@PathVariable("captchaWidth") @Max(1920) @Min(130) Integer captchaWidth,
                                                @PathVariable("captchaHeight") @Max(1080) @Min(48) Integer captchaHeight,
                                                HttpServletResponse response) {
        // 获取运算的结果
        Captcha captcha = CaptchaUtil.getCaptcha(captchaWidth, captchaHeight);
        //当验证码类型为 arithmetic时且长度 >= 2 时，captcha.text()的结果有几率为浮点型
        String captchaValue = captcha.text();
        if (captcha.getCharType() - 1 == Captcha.TYPE_DEFAULT && captchaValue.contains(".")) {
            captchaValue = RegexUtil.DOT.split(captchaValue)[0];
        }
        String id = captchaService.save(captchaValue);
        //add no cache headers
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        // 验证码信息
        return ApiResult.success(new CaptchaDTO(id, captcha.toBase64()));
    }

    /**
     * 注销登陆
     *
     * @return 结果
     */
    @DeleteMapping(value = "/logout")
    public ApiResult<String> logout() {
        onlineUserService.logout();
        SecurityContextHolder.clearContext();
        return ApiResult.success();
    }
}
