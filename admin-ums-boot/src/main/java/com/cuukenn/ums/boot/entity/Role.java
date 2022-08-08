package com.cuukenn.ums.boot.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.cuukenn.starter.jpa.entity.AbstractEntity;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import java.util.HashSet;
import java.util.Set;

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
    @Column
    @Length(max = 100)
    private String role;

    @ManyToMany(mappedBy = "authorities", fetch = FetchType.EAGER, cascade = {CascadeType.MERGE})
    @JsonIgnore
    @ToString.Exclude
    private Set<User> users = new HashSet<>();

    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
