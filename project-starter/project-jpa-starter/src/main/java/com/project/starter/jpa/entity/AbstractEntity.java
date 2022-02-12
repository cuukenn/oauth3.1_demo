package com.project.starter.jpa.entity;

import com.project.core.base.BaseEntity;
import com.project.core.constant.Constant;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * JPA的实体表基类
 *
 * @author changgg
 */
@Getter
@Setter
@RequiredArgsConstructor
@ToString
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class AbstractEntity implements BaseEntity {
    private static final long serialVersionUID = -2810104515468076509L;
    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = Constant.ID)
    @ToString.Exclude
    private Long id;
    /**
     * 创建人
     */
    @Column(name = Constant.CREATED_BY)
    @CreatedBy
    private String createBy;
    /**
     * 更新人
     */
    @Column(name = Constant.LAST_MODIFIED_BY)
    @LastModifiedBy
    private String lastModifiedBy;
    /**
     * 创建时间
     */
    @Column(name = Constant.CREATED_TIME)
    @CreatedDate
    private LocalDateTime createTime;
    /**
     * 更新时间
     */
    @Column(name = Constant.LAST_MODIFIED_TIME)
    @LastModifiedDate
    private LocalDateTime lastModifiedTime;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) {
            return false;
        }
        AbstractEntity that = (AbstractEntity) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
