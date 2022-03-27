package com.cuukenn.auth.boot.security.core;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.security.oauth2.core.OAuth2TokenType;
import org.springframework.security.oauth2.server.authorization.JdbcOAuth2AuthorizationService;
import org.springframework.security.oauth2.server.authorization.OAuth2Authorization;
import org.springframework.security.oauth2.server.authorization.OAuth2AuthorizationService;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.stereotype.Service;

/**
 * @author changgg
 */
@SuppressWarnings("AlibabaClassNamingShouldBeCamel")
@Service
@Slf4j
public class CustomerOAuth2AuthorizationServiceImpl implements OAuth2AuthorizationService {
    private final OAuth2AuthorizationService delegate;

    public CustomerOAuth2AuthorizationServiceImpl(JdbcOperations jdbcOperations, RegisteredClientRepository repository) {
        this.delegate = new JdbcOAuth2AuthorizationService(jdbcOperations, repository);
    }

    @Override
    public void save(OAuth2Authorization oAuth2Authorization) {
        this.delegate.save(oAuth2Authorization);
    }

    @Override
    public void remove(OAuth2Authorization oAuth2Authorization) {
        this.delegate.remove(oAuth2Authorization);
    }

    @Override
    public OAuth2Authorization findById(String id) {
        return this.delegate.findById(id);
    }

    @Override
    public OAuth2Authorization findByToken(String token, OAuth2TokenType oAuth2TokenType) {
        return this.delegate.findByToken(token, oAuth2TokenType);
    }
}
