package com.project.auth.boot.security.core;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.security.oauth2.server.authorization.client.JdbcRegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.stereotype.Service;

/**
 * @author changgg
 */
@Service
@Slf4j
public class CustomerRegisteredClientRepositoryImpl implements RegisteredClientRepository {
    private final RegisteredClientRepository delegate;

    public CustomerRegisteredClientRepositoryImpl(JdbcOperations jdbcOperations) {
        this.delegate = new JdbcRegisteredClientRepository(jdbcOperations);
    }

    @Override
    public void save(RegisteredClient registeredClient) {
        this.delegate.save(registeredClient);
    }

    @Override
    public RegisteredClient findById(String id) {
        return this.delegate.findById(id);
    }

    @Override
    public RegisteredClient findByClientId(String clientId) {
        return this.delegate.findByClientId(clientId);
    }
}
