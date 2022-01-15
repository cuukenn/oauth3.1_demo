package com.project.ums.server.web;

import cn.hutool.core.map.MapUtil;
import cn.hutool.crypto.digest.MD5;
import com.project.common.api.ApiResult;
import com.project.common.exception.BizException;
import com.project.common.util.CaptchaUtil;
import com.project.common.util.RegexUtil;
import com.project.security.annotation.DeleteAnonymousAccess;
import com.project.security.annotation.GetAnonymousAccess;
import com.project.security.annotation.PostAnonymousAccess;
import com.project.ums.server.dto.AuthUserDTO;
import com.wf.captcha.base.Captcha;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.security.access.annotation.Secured;
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
import security.service.ICaptchaService;

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

    /**
     * 用户登陆
     *
     * @param authUserDTO 登陆DTO
     * @return 登陆结果
     */
    @PostMapping(value = "/login")
    @PostAnonymousAccess
    public ApiResult<Object> login(AuthUserDTO authUserDTO) {
        ApiResult<Object> response;
        try {
            String exceptedCaptcha = captchaService.removeIfAbstract(authUserDTO.getCaptchaId());
            if (exceptedCaptcha == null) {
                throw new BizException("验证码失效");
            }
            if (!Objects.equals(exceptedCaptcha, authUserDTO.getCaptchaCode())) {
                throw new BizException("验证码错误");
            }
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                    authUserDTO.getUsername(),
                    authUserDTO.getPassword()
            );
            Authentication authenticate = authenticationManagerBuilder.getObject().authenticate(authentication);
            SecurityContextHolder.getContext().setAuthentication(authenticate);
            String token = null;
            // 返回 token 与 用户信息
            response = ApiResult.success(MapUtil.builder().put("token", token).put("user", null).build());
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
    public ApiResult<Object> login(String username, String password) {
        String captchaCode = "only for test";
        String captchaId = captchaService.save(captchaCode);
        AuthUserDTO authUserDTO = new AuthUserDTO();
        authUserDTO.setUsername(username);
        authUserDTO.setPassword(MD5.create().digestHex(password, StandardCharsets.UTF_8));
        authUserDTO.setCaptchaId(captchaId);
        authUserDTO.setCaptchaCode(captchaCode);
        return login(authUserDTO);
    }

    /**
     * 获取验证码
     *
     * @param response response
     * @return 验证码
     */
    @GetMapping(value = "/captchaCode")
    @GetAnonymousAccess
    public ApiResult<Object> getCaptchaCode(HttpServletResponse response) {
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
    public ApiResult<Object> getCaptchaCode(@PathVariable("captchaWidth") @Max(1920) @Min(130) Integer captchaWidth,
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
        com.wf.captcha.utils.CaptchaUtil.setHeader(response);
        // 验证码信息
        return ApiResult.success(MapUtil.builder().put("captchaId", id).put("captchaIdImg", captcha.toBase64()));
    }

    /**
     * 注销登陆
     *
     * @return 结果
     */
    @DeleteAnonymousAccess
    @DeleteMapping(value = "/logout")
    public ApiResult<String> logout() {
        return ApiResult.success();
    }

    /**
     * 踢人
     *
     * @param token 令牌
     * @return 结果
     */
    @SuppressWarnings("SpellCheckingInspection")
    @DeleteMapping(value = "/kickout")
    @Secured("ADMIN")
    public ApiResult<Void> kickOut(String token) {
        return ApiResult.success();
    }
}
