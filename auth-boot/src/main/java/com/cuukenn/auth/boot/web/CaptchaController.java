package com.cuukenn.auth.boot.web;

import com.cuukenn.auth.boot.service.ICaptchaService;
import com.cuukenn.auth.boot.pojo.query.CaptchaCodeQuery;
import com.cuukenn.auth.boot.pojo.query.CaptchaDTO;
import com.cuukenn.auth.boot.util.CaptchaUtil;
import com.cuukenn.core.api.ApiResult;
import com.cuukenn.core.util.RegexUtil;
import com.wf.captcha.base.Captcha;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

/**
 * 验证码API
 *
 * @author changgg
 */
@RestController
@RequestMapping("/api/captcha")
@RequiredArgsConstructor
public class CaptchaController {
    private final ICaptchaService captchaService;

    /**
     * 获取验证码(长130,宽48)
     *
     * @param response response
     * @return 验证码
     */
    @GetMapping(value = "/captchaCode")
    public ApiResult<CaptchaDTO> getCaptchaCode(HttpServletResponse response) {
        return getCaptchaCode(new CaptchaCodeQuery(130, 48), response);
    }

    /**
     * 获取验证码
     *
     * @param query    查询对象
     * @param response response
     * @return 验证码
     */
    @GetMapping(value = "/captchaCode/{captchaWidth}/{captchaHeight}")
    public ApiResult<CaptchaDTO> getCaptchaCode(CaptchaCodeQuery query, HttpServletResponse response) {
        // 获取运算的结果
        Captcha captcha = CaptchaUtil.getCaptcha(query.getCaptchaWidth(), query.getCaptchaHeight());
        //当验证码类型为 arithmetic时且长度 >= 2 时，captcha.text()的结果有几率为浮点型
        String captchaValue = captcha.text();
        if (captcha.getCharType() - 1 == Captcha.TYPE_DEFAULT && captchaValue.contains(".")) {
            captchaValue = RegexUtil.DOT.split(captchaValue)[0];
        }
        String id = captchaService.save(captchaValue);
        //add no cache headers
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0L);
        // 验证码信息
        return ApiResult.success(new CaptchaDTO(id, captcha.toBase64()));
    }
}
