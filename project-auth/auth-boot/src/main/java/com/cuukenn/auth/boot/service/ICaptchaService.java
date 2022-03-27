package com.cuukenn.auth.boot.service;

/**
 * 验证码字符串存取服务
 *
 * @author changgg
 */
public interface ICaptchaService {
    /**
     * 使用指定ID获取对应的验证码结果
     * 存在结果将数据清空后进行返回
     *
     * @param id 验证码ID
     * @return 验证码内容{可以为null}
     */
    String removeIfAbstract(String id);

    /**
     * 将验证码进行保存
     *
     * @param code 验证码
     * @return 验证码ID
     */
    String save(String code);
}
