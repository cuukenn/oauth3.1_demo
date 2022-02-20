package com.project.ums.server.entity;

import com.project.starter.jpa.entity.AbstractEntity;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Max;

/**
 * @author changgg
 */
@SuppressWarnings("JpaDataSourceORMInspection")
@Entity
@Table(name = "sys_role", uniqueConstraints = {@UniqueConstraint(name = "uix_role", columnNames = {Role_.ROLE})})
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Role extends AbstractEntity {
    private static final long serialVersionUID = 4169368215586374810L;
    @Column(name = "role")
    @Max(20L)
    private String role;

    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
