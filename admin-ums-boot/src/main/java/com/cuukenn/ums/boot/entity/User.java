package com.cuukenn.ums.boot.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.cuukenn.core.validate.Telephone;
import com.cuukenn.starter.jpa.entity.AbstractEntity;
import com.cuukenn.ums.boot.enums.GenderConverter;
import com.cuukenn.ums.boot.enums.GenderEnum;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Email;
import java.time.LocalDate;
import java.util.Set;

/**
 * @author changgg
 */
@SuppressWarnings("JpaDataSourceORMInspection")
@Entity
@Table(name = "sys_user", uniqueConstraints = {
        @UniqueConstraint(name = "uix_username", columnNames = {User_.USERNAME, User_.EMAIL, User_.TELEPHONE})
})
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class User extends AbstractEntity {
    private static final long serialVersionUID = -3252961549936053753L;
    /**
     * 用户名
     */
    @Column(unique = true, nullable = false)
    @Length(max = 12)
    private String username;
    /**
     * 密码
     */
    @ToString.Exclude
    @Column(nullable = false)
    @Length(max = 128)
    private String password;
    /**
     * 账户未过期?
     */
    @Column
    private boolean accountNonExpired;
    /**
     * 账户未被锁?
     */
    @Column
    private boolean accountNonLocked;
    /**
     * 账户授权未过期?
     */
    @Column
    private boolean credentialsNonExpired;
    /**
     * 账户是否有效？
     */
    @Column
    private boolean enabled;
    /**
     * 用户角色
     */
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinTable(name = "sys_user_role",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
    @JsonIgnore
    @ToString.Exclude
    private Set<Role> authorities;
    /**
     * 昵称
     */
    @Column(nullable = false)
    @Length(max = 12)
    private String nickName;
    /**
     * 性别
     */
    @Column
    @Convert(converter = GenderConverter.class)
    private GenderEnum sex;
    /**
     * 手机号
     */
    @Column(unique = true)
    @Telephone
    private String telephone;
    /**
     * 电子邮件地址
     */
    @Column(unique = true)
    @Email
    private String email;
    /**
     * 出生日期
     */
    @Column
    private LocalDate birthDate;

    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
