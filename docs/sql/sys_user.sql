INSERT INTO sys_user (create_by, create_time, last_modified_by, last_modified_time, account_non_expired,
                      account_non_locked, birth_date, credentials_non_expired, email, enabled, nick_name, password, sex,
                      telephone, username)
VALUES ('anonymous', '2022-03-31 13:22:45.086000000', 'anonymous', '2022-03-31 13:22:45.086000000', 1, 1, NULL, 1,
        'super_admin@mail.com', 1, 'super_admin',
        '{bcrypt}$2a$10$WbfrFrq2kLzMui2xK0TxNeSZ5ZBmUaxzOwmcMwraUY3kRhPCMOHoa', -1, '15212546854', 'super_admin'),
       ('anonymous', '2022-03-31 13:22:45.203000000', 'anonymous', '2022-03-31 13:22:45.203000000', 1, 1, NULL, 1,
        'adminRole@mail.com', 1, 'admin', '{bcrypt}$2a$10$w3YgZoOsxCKyCvCba6i9Xux3wFZwWGUtowlbMsofupF354K7RwaG2', -1,
        '15212546855', 'admin'),
       ('anonymous', '2022-03-31 13:22:45.299000000', 'anonymous', '2022-03-31 13:22:45.299000000', 1, 1, NULL, 1,
        'userRole@mail.com', 1, 'user', '{bcrypt}$2a$10$WSfu.Z4/ornpsHK9oUQQVujULZjJLkzXDVe.hvPI25wBzThMeWo8u', -1,
        '15212546856', 'user');