package com.project.common.util;

import com.wf.captcha.ArithmeticCaptcha;
import com.wf.captcha.ChineseCaptcha;
import com.wf.captcha.ChineseGifCaptcha;
import com.wf.captcha.GifCaptcha;
import com.wf.captcha.SpecCaptcha;
import com.wf.captcha.base.Captcha;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ThreadLocalRandom;

/**
 * 验证码工具类
 *
 * @author changgg
 */
@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class CaptchaUtil {
    /**
     * 随机获取验证码类型
     *
     * @return 验证码实例
     */
    @SuppressWarnings("DuplicateBranchesInSwitch")
    public static Captcha getCaptcha(int captchaWidth, int captchaHeight) {
        switch (ThreadLocalRandom.current().nextInt(5)) {
            case 0:
                return new GifCaptcha(captchaWidth, captchaHeight);
            case 1:
                return new SpecCaptcha(captchaWidth, captchaHeight);
            case 2:
                return new ArithmeticCaptcha(captchaWidth, captchaHeight);
            case 3:
                return new ChineseCaptcha(captchaWidth, captchaHeight);
            case 4:
                return new ChineseGifCaptcha(captchaWidth, captchaHeight);
            default:
                return new ArithmeticCaptcha(captchaWidth, captchaHeight);
        }
    }
}
