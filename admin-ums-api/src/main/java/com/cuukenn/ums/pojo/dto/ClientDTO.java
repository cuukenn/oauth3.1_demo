package com.cuukenn.ums.pojo.dto;

import com.cuukenn.core.base.BaseDTO;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

/**
 * client实体传输对象
 *
 * @author changgg
 */
@RequiredArgsConstructor
@ToString
@Data
public class ClientDTO implements BaseDTO {
    private static final long serialVersionUID = 1263909560507249267L;
    private String clientId;
    private String clientSecret;
    private Integer accessTokenValiditySeconds;
    private Integer refreshTokenValiditySeconds;
    private String scope;
    private String resourceIds;
    private String registeredRedirectUris;
    private String authorizedGrantTypes;
    private String autoApproveScopes;
    private String authorities;
    private String additionalInformation;
}
