package com.cuukenn.ums.boot;

import com.cuukenn.core.constant.Constant;
import com.cuukenn.ums.boot.entity.Role;
import com.cuukenn.ums.boot.entity.User;
import com.cuukenn.ums.boot.enums.GenderEnum;
import com.cuukenn.ums.boot.repository.RoleRepository;
import com.cuukenn.ums.boot.repository.UserRepository;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collections;
import java.util.HashSet;

/**
 * @author changgg
 */
@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AddInitUserTest {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;

    /**
     * 增加默认的角色
     */
    @Test
    @Order(1)
    public void test_add_init_role() {
        Role superAdminRole = roleRepository.findById(1L).orElse(new Role());
        Role adminRole = roleRepository.findById(2L).orElse(new Role());
        Role userRole = roleRepository.findById(3L).orElse(new Role());
        {
            superAdminRole.setId(1L);
            superAdminRole.setRole(Constant.ROLE_SUPER_ADMIN);
            roleRepository.save(superAdminRole);
        }
        {
            adminRole.setId(2L);
            adminRole.setRole(Constant.ROLE_ADMIN);
            roleRepository.save(adminRole);
        }
        {
            userRole.setId(3L);
            userRole.setRole(Constant.ROLE_USER);
            roleRepository.save(userRole);
        }
    }

    /**
     * 增加默认的用户
     */
    @Order(2)
    @SuppressWarnings("OptionalGetWithoutIsPresent")
    @Test
    public void test_add_init_user() {
        PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        {
            User entity = userRepository.findById(1L).orElse(new User());
            entity.setId(1L);
            entity.setUsername("super_admin");
            entity.setNickName("super_admin");
            entity.setSex(GenderEnum.NOT_SET);
            entity.setEmail("super_admin@mail.com");
            entity.setTelephone("15212546854");
            entity.setPassword(passwordEncoder.encode("123456"));
            entity.setEnabled(true);
            entity.setAccountNonExpired(true);
            entity.setAccountNonLocked(true);
            entity.setCredentialsNonExpired(true);
            userRepository.save(entity);
        }
        {
            User entity = userRepository.findById(2L).orElse(new User());
            entity.setId(2L);
            entity.setUsername("admin");
            entity.setNickName("admin");
            entity.setSex(GenderEnum.NOT_SET);
            entity.setEmail("adminRole@mail.com");
            entity.setTelephone("15212546855");
            entity.setPassword(passwordEncoder.encode("123456"));
            entity.setEnabled(true);
            entity.setAccountNonExpired(true);
            entity.setAccountNonLocked(true);
            entity.setCredentialsNonExpired(true);
            userRepository.save(entity);
        }
        {
            User entity = userRepository.findById(3L).orElse(new User());
            entity.setId(3L);
            entity.setUsername("user");
            entity.setNickName("user");
            entity.setSex(GenderEnum.NOT_SET);
            entity.setEmail("userRole@mail.com");
            entity.setTelephone("15212546856");
            entity.setPassword(passwordEncoder.encode("123456"));
            entity.setEnabled(true);
            entity.setAccountNonExpired(true);
            entity.setAccountNonLocked(true);
            entity.setCredentialsNonExpired(true);
            userRepository.save(entity);
        }
    }

    /**
     * 增加默认的用户权限绑定
     */
    @Order(3)
    @SuppressWarnings("OptionalGetWithoutIsPresent")
    @Test
    public void test_add_init_user_role() {
        Role superAdminRole = roleRepository.findById(1L).get();
        Role adminRole = roleRepository.findById(2L).get();
        Role userRole = roleRepository.findById(3L).get();

        User superAdminUser = userRepository.findById(1L).get();
        superAdminUser.setAuthorities(new HashSet<>(Collections.singletonList(superAdminRole)));
        userRepository.save(superAdminUser);

        User adminUser = userRepository.findById(2L).get();
        adminUser.setAuthorities(new HashSet<>(Collections.singletonList(adminRole)));
        userRepository.save(adminUser);

        User userUser = userRepository.findById(3L).get();
        userUser.setAuthorities(new HashSet<>(Collections.singletonList(userRole)));
        userRepository.save(userUser);
    }
}
