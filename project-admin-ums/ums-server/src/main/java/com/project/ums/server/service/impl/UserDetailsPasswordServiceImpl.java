package com.project.ums.server.service.impl;

import com.project.ums.server.entity.User;
import com.project.ums.server.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsPasswordService;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

/**
 * 在修改加密方式后，一旦登陆成功则使用新加密方式持久化密码
 *
 * @author changgg
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class UserDetailsPasswordServiceImpl implements UserDetailsPasswordService {
    private final UserRepository repository;

    @Override
    public UserDetails updatePassword(UserDetails user, String newPassword) {
        User entity = repository.findByUsername(user.getUsername()).orElse(null);
        if (entity != null) {
            log.debug("使用新加密方式进行密码持久化,username:[{}]", user.getUsername());
            entity.setPassword(newPassword);
            repository.save(entity);
            return new org.springframework.security.core.userdetails.User(
                    entity.getUsername(),
                    entity.getPassword(),
                    entity.isEnabled(),
                    entity.isAccountNonExpired(),
                    entity.isCredentialsNonExpired(),
                    entity.isAccountNonLocked(),
                    entity.getAuthorities().stream().map(role -> new SimpleGrantedAuthority(role.getRole())).collect(Collectors.toList())
            );
        } else {
            log.debug("使用新加密方式更新密码时无法查询到指定的用户名[{}],更新失败", user.getUsername());
            return user;
        }
    }
}
