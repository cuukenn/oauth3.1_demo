package com.cuukenn.auth.boot.security.core;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.security.oauth2.server.authorization.JdbcOAuth2AuthorizationConsentService;
import org.springframework.security.oauth2.server.authorization.OAuth2AuthorizationConsent;
import org.springframework.security.oauth2.server.authorization.OAuth2AuthorizationConsentService;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.stereotype.Service;

/**
 * @author changgg
 */
@SuppressWarnings("AlibabaClassNamingShouldBeCamel")
@Service
@Slf4j
public class CustomerOAuth2AuthorizationConsentServiceImpl implements OAuth2AuthorizationConsentService {
    private final OAuth2AuthorizationConsentService delegate;

    public CustomerOAuth2AuthorizationConsentServiceImpl(JdbcOperations jdbcOperations, RegisteredClientRepository repository) {
        this.delegate = new JdbcOAuth2AuthorizationConsentService(jdbcOperations, repository);
    }

    @Override
    public void save(OAuth2AuthorizationConsent oAuth2AuthorizationConsent) {
        delegate.save(oAuth2AuthorizationConsent);
    }

    @Override
    public void remove(OAuth2AuthorizationConsent oAuth2AuthorizationConsent) {
        delegate.remove(oAuth2AuthorizationConsent);
    }

    @Override
    public OAuth2AuthorizationConsent findById(String registeredClientId, String principalName) {
        return delegate.findById(registeredClientId, principalName);
    }
}
