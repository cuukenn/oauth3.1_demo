-- cuukenn.oauth2_authorization definition

CREATE TABLE `oauth2_authorization` (
                                        `id` varchar(100) NOT NULL,
                                        `registered_client_id` varchar(100) NOT NULL,
                                        `principal_name` varchar(200) NOT NULL,
                                        `authorization_grant_type` varchar(100) NOT NULL,
                                        `attributes` blob,
                                        `state` varchar(500) DEFAULT NULL,
                                        `authorization_code_value` blob,
                                        `authorization_code_issued_at` timestamp NULL DEFAULT NULL,
                                        `authorization_code_expires_at` timestamp NULL DEFAULT NULL,
                                        `authorization_code_metadata` blob,
                                        `access_token_value` blob,
                                        `access_token_issued_at` timestamp NULL DEFAULT NULL,
                                        `access_token_expires_at` timestamp NULL DEFAULT NULL,
                                        `access_token_metadata` blob,
                                        `access_token_type` varchar(100) DEFAULT NULL,
                                        `access_token_scopes` varchar(1000) DEFAULT NULL,
                                        `oidc_id_token_value` blob,
                                        `oidc_id_token_issued_at` timestamp NULL DEFAULT NULL,
                                        `oidc_id_token_expires_at` timestamp NULL DEFAULT NULL,
                                        `oidc_id_token_metadata` blob,
                                        `refresh_token_value` blob,
                                        `refresh_token_issued_at` timestamp NULL DEFAULT NULL,
                                        `refresh_token_expires_at` timestamp NULL DEFAULT NULL,
                                        `refresh_token_metadata` blob,
                                        PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- cuukenn.oauth2_authorization_consent definition

CREATE TABLE `oauth2_authorization_consent` (
                                                `registered_client_id` varchar(100) NOT NULL,
                                                `principal_name` varchar(200) NOT NULL,
                                                `authorities` varchar(1000) NOT NULL,
                                                PRIMARY KEY (`registered_client_id`,`principal_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- cuukenn.oauth2_registered_client definition

CREATE TABLE `oauth2_registered_client` (
                                            `id` varchar(100) NOT NULL,
                                            `client_id` varchar(100) NOT NULL,
                                            `client_id_issued_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
                                            `client_secret` varchar(200) DEFAULT NULL,
                                            `client_secret_expires_at` timestamp NULL DEFAULT NULL,
                                            `client_name` varchar(200) NOT NULL,
                                            `client_authentication_methods` varchar(1000) NOT NULL,
                                            `authorization_grant_types` varchar(1000) NOT NULL,
                                            `redirect_uris` varchar(1000) DEFAULT NULL,
                                            `scopes` varchar(1000) NOT NULL,
                                            `client_settings` varchar(2000) NOT NULL,
                                            `token_settings` varchar(2000) NOT NULL,
                                            PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- cuukenn.sys_role definition

CREATE TABLE `sys_role` (
                            `id` bigint NOT NULL AUTO_INCREMENT,
                            `create_by` varchar(255) NOT NULL,
                            `create_time` datetime(6) NOT NULL,
                            `last_modified_by` varchar(255) NOT NULL,
                            `last_modified_time` datetime(6) NOT NULL,
                            `role` varchar(255) DEFAULT NULL,
                            PRIMARY KEY (`id`),
                            UNIQUE KEY `uix_role` (`role`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- cuukenn.sys_user definition

CREATE TABLE `sys_user` (
                            `id` bigint NOT NULL AUTO_INCREMENT,
                            `create_by` varchar(255) NOT NULL,
                            `create_time` datetime(6) NOT NULL,
                            `last_modified_by` varchar(255) NOT NULL,
                            `last_modified_time` datetime(6) NOT NULL,
                            `account_non_expired` bit(1) DEFAULT NULL,
                            `account_non_locked` bit(1) DEFAULT NULL,
                            `birth_date` date DEFAULT NULL,
                            `credentials_non_expired` bit(1) DEFAULT NULL,
                            `email` varchar(255) DEFAULT NULL,
                            `enabled` bit(1) DEFAULT NULL,
                            `nick_name` varchar(255) NOT NULL,
                            `password` varchar(255) NOT NULL,
                            `sex` int DEFAULT NULL,
                            `telephone` varchar(255) DEFAULT NULL,
                            `username` varchar(255) NOT NULL,
                            PRIMARY KEY (`id`),
                            UNIQUE KEY `UK_51bvuyvihefoh4kp5syh2jpi4` (`username`),
                            UNIQUE KEY `uix_username` (`username`,`email`,`telephone`),
                            UNIQUE KEY `UK_ahtq5ew3v0kt1n7hf1sgp7p8l` (`email`),
                            UNIQUE KEY `UK_5yjscty3x2ikebjxas9l0cwl7` (`telephone`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- cuukenn.sys_user_role definition

CREATE TABLE `sys_user_role` (
                                 `user_id` bigint NOT NULL,
                                 `role_id` bigint NOT NULL,
                                 PRIMARY KEY (`user_id`,`role_id`),
                                 KEY `FKhh52n8vd4ny9ff4x9fb8v65qx` (`role_id`),
                                 CONSTRAINT `FKb40xxfch70f5qnyfw8yme1n1s` FOREIGN KEY (`user_id`) REFERENCES `sys_user` (`id`),
                                 CONSTRAINT `FKhh52n8vd4ny9ff4x9fb8v65qx` FOREIGN KEY (`role_id`) REFERENCES `sys_role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;