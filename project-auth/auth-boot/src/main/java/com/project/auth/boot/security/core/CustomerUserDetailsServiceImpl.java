package com.project.auth.boot.security.core;

import com.project.core.api.ApiResult;
import com.project.core.exception.BizException;
import com.project.ums.api.UserManageFeignClient;
import com.project.ums.pojo.dto.UserAuthDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * 用户服务
 *
 * @author changgg
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class CustomerUserDetailsServiceImpl implements UserDetailsService {
    private final UserManageFeignClient userManageFeignClient;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserAuthDTO> validate = ApiResult.validate(userManageFeignClient.findByUsername(username));
        if (validate.isPresent()) {
            UserAuthDTO dto = validate.get();
            return new User(
                    dto.getUsername(), dto.getPassword(),
                    dto.getEnabled(),
                    dto.getAccountNonExpired(), dto.getCredentialsNonExpired(), dto.getAccountNonLocked(),
                    Optional.ofNullable(dto.getAuthorities())
                            .map(item -> item.stream().map(role -> new SimpleGrantedAuthority(role.getRole())).collect(Collectors.toList()))
                            .orElse(Collections.emptyList())
            );
        } else {
            throw new BizException("未获取到指定的用户数据,username:" + username);
        }
    }
}
