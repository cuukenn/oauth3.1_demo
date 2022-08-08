package com.cuukenn.auth.boot;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.core.oidc.OidcScopes;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.config.ClientSettings;

import java.util.UUID;

/**
 * @author changgg
 */
@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AddInitOAuthClientTest {
    @Autowired
    private RegisteredClientRepository registeredClientRepository;

    /*
     * 增加默认客户端数据
     */
    @Test
    @Order(1)
    public void test_add_init_oauth_client() {
        RegisteredClient registeredClient = registeredClientRepository.findByClientId("system-client");
        if (registeredClient != null) {
            registeredClient = RegisteredClient.from(registeredClient)
                    .clientId("system-client")
                    .clientSecret("{noop}secret")
                    .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
                    .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
                    .authorizationGrantType(AuthorizationGrantType.REFRESH_TOKEN)
                    .authorizationGrantType(AuthorizationGrantType.CLIENT_CREDENTIALS)
                    .redirectUri("http://127.0.0.1:8080/login/oauth2/code/system-client-oidc")
                    .scope(OidcScopes.OPENID)
                    .scope("system-client.read")
                    .scope("system-client.write")
                    .clientSettings(ClientSettings.builder().requireAuthorizationConsent(true).build())
                    .build();
        } else {
            registeredClient = RegisteredClient.withId(UUID.randomUUID().toString())
                    .clientId("system-client")
                    .clientSecret("{noop}secret")
                    .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
                    .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
                    .authorizationGrantType(AuthorizationGrantType.REFRESH_TOKEN)
                    .authorizationGrantType(AuthorizationGrantType.CLIENT_CREDENTIALS)
                    .redirectUri("http://127.0.0.1:8080/login/oauth2/code/system-client-oidc")
                    .scope(OidcScopes.OPENID)
                    .scope("system-client.read")
                    .scope("system-client.write")
                    .clientSettings(ClientSettings.builder().requireAuthorizationConsent(true).build())
                    .build();
        }
        registeredClientRepository.save(registeredClient);
    }
}
