INSERT INTO oauth2_registered_client(id,
                                     client_id,
                                     client_id_issued_at,
                                     client_secret,
                                     client_name,
                                     client_authentication_methods,
                                     authorization_grant_types,
                                     redirect_uris,
                                     scopes,
                                     client_settings,
                                     token_settings)
VALUES ('23466857-51ee-44ac-906a-c1b4343754eb',
        'system-client',
        '2022-01-01 00:00:00',
        '{noop}secret',
        '23466857-51ee-44ac-906a-c1b4343754eb',
        'client_secret_basic',
        'refresh_token,client_credentials,authorization_code',
        'http://127.0.0.1:8080/authorized',
        'read,write',
        '{"@class":"java.util.Collections$UnmodifiableMap","settings.client.require-proof-key":false,"settings.client.require-authorization-consent":true}',
        '{"@class":"java.util.Collections$UnmodifiableMap","settings.token.reuse-refresh-tokens":true,"settings.token.id-token-signature-algorithm":["org.springframework.security.oauth2.jose.jws.SignatureAlgorithm","RS256"],"settings.token.access-token-time-to-live":["java.time.Duration",300.000000000],"settings.token.refresh-token-time-to-live":["java.time.Duration",3600.000000000]}');