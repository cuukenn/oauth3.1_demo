package com.cuukenn.auth.boot.api;

import com.cuukenn.auth.boot.Constant;
import com.cuukenn.core.api.ApiResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 认证API
 *
 * @author changgg
 */
@SuppressWarnings("AlibabaClassNamingShouldBeCamel")
@FeignClient(name = Constant.API_AUTH_BOOT, contextId = "AuthenticationFeignClient")
@RequestMapping(value = "/api/auth")
public interface AuthenticationFeignClient {
    /**
     * 获取JWT公钥API
     *
     * @return 数据
     */
    @RequestMapping(value = "/getPublicKey", method = RequestMethod.GET)
    ApiResult<String> getPublicKey();
}
