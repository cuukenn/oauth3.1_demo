package com.project.ums.server.poj.command;

import com.project.core.base.BaseCommand;
import com.project.security.pojo.TokenPair;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * 接收令牌刷新请求
 *
 * @author changgg
 */
@Data
public class RefreshTokenCommand implements BaseCommand {
    private static final long serialVersionUID = 6815645370458309153L;
    /**
     * 令牌
     */
    @NotEmpty
    private String token;
    /**
     * 刷新令牌
     */
    @NotEmpty
    private String refreshToken;

    /**
     * 转化为实际的令牌对
     *
     * @return 令牌对
     */
    public TokenPair toTokenPair() {
        return new TokenPair(token, refreshToken);
    }
}
