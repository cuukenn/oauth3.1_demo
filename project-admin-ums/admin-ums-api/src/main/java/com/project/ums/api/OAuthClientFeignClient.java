package com.project.ums.api;

import com.project.core.api.ApiResult;
import com.project.ums.Constant;
import com.project.ums.pojo.dto.ClientDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * OAuth客户端API
 *
 * @author changgg
 */
@SuppressWarnings("AlibabaClassNamingShouldBeCamel")
@FeignClient(name = Constant.API_UMS_BOOT, contextId = "OAuthClientFeignClient")
@RequestMapping(value = "/api/client")
public interface OAuthClientFeignClient {
    /**
     * 根据clientId获取客户端数据
     *
     * @param clientId 客户端ID
     * @return 数据
     */
    @RequestMapping(value = "/getByClientDetails", method = RequestMethod.GET)
    ApiResult<ClientDTO> getByClientDetails(@RequestParam String clientId);
}
