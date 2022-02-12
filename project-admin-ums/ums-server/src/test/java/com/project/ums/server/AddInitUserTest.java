package com.project.ums.server;

import com.project.core.constant.Constant;
import com.project.ums.server.entity.Role;
import com.project.ums.server.entity.User;
import com.project.ums.server.enums.GenderEnum;
import com.project.ums.server.repository.RoleRepository;
import com.project.ums.server.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collections;

/**
 * @author changgg
 */
@SpringBootTest
public class AddInitUserTest {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * 增加默认的用户及角色
     */
    @Test
    public void test_add_init_user() {
        Role superAdmin = new Role();
        {
            superAdmin.setId(1L);
            superAdmin.setRole(Constant.ROLE_SUPER_ADMIN);
            roleRepository.save(superAdmin);
        }
        Role admin = new Role();
        {
            admin.setId(2L);
            admin.setRole(Constant.ROLE_ADMIN);
            roleRepository.save(admin);
        }
        Role user = new Role();
        {
            user.setId(3L);
            user.setRole(Constant.ROLE_USER);
            roleRepository.save(user);
        }
        {
            User entity = new User();
            entity.setUsername("super_admin");
            entity.setNickName("super_admin");
            entity.setSex(GenderEnum.NOT_SET);
            entity.setEmail("super_admin@mail.com");
            entity.setTelephone("2345678910");
            entity.setAuthorities(Collections.singleton(superAdmin));
            entity.setPassword(passwordEncoder.encode("123456@SuperAdmin"));
            userRepository.save(entity);
        }
        {
            User entity = new User();
            entity.setUsername("admin");
            entity.setNickName("admin");
            entity.setSex(GenderEnum.NOT_SET);
            entity.setEmail("admin@mail.com");
            entity.setTelephone("12345678910");
            entity.setAuthorities(Collections.singleton(admin));
            entity.setPassword(passwordEncoder.encode("123456@Admin"));
            userRepository.save(entity);
        }
        {
            User entity = new User();
            entity.setUsername("user");
            entity.setNickName("user");
            entity.setSex(GenderEnum.NOT_SET);
            entity.setEmail("user@mail.com");
            entity.setTelephone("10987654321");
            entity.setAuthorities(Collections.singleton(user));
            entity.setPassword(passwordEncoder.encode("123456@User"));
            userRepository.save(entity);
        }
    }
}
