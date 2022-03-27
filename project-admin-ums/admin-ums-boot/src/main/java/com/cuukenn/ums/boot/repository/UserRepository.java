package com.cuukenn.ums.boot.repository;

import com.cuukenn.ums.boot.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author changgg
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {
    /**
     * 根据用户名查询指定用户
     *
     * @param username 用户名
     * @return entity
     */
    Optional<User> findByUsername(String username);
}
