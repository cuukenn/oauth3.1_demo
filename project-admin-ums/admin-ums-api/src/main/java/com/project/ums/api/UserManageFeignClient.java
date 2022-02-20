package com.project.ums.api;

import com.project.core.api.ApiResult;
import com.project.ums.Constant;
import com.project.ums.pojo.dto.UserAuthDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 用户管理API
 *
 * @author changgg
 */
@SuppressWarnings("AlibabaClassNamingShouldBeCamel")
@FeignClient(name = Constant.API_UMS_BOOT, contextId = "UserManageFeignClient")
@RequestMapping("/api/user/manage")
public interface UserManageFeignClient {
    /**
     * 根据clientId获取客户端数据
     *
     * @param username 用户名
     * @return 数据
     */
    @RequestMapping(value = "/findByUsername", method = RequestMethod.GET)
    ApiResult<UserAuthDTO> findByUsername(@RequestParam String username);
}
