package com.project.security.service;

import com.project.security.pojo.TokenPair;

/**
 * @author changgg
 */
public interface IOnlineUserService {
    /**
     * 保存令牌
     *
     * @param tokenPair  令牌对
     * @param rememberMe 记住我
     */
    void save(TokenPair tokenPair, boolean rememberMe);

    /**
     * 刷新保存的令牌
     *
     * @param tokenPair 令牌对
     */
    void refresh(TokenPair tokenPair);

    /**
     * 指定的令牌对是否存在
     *
     * @param tokenPair 令牌对
     * @return 是否存在
     */
    boolean validate(TokenPair tokenPair);

    /**
     * 注销登陆
     */
    void logout();
}
