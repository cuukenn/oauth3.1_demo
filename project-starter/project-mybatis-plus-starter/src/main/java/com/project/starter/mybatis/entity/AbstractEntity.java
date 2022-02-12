package com.project.starter.mybatis.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.project.core.base.BaseEntity;
import com.project.core.constant.Constant;
import lombok.Data;
import lombok.ToString;

import java.time.LocalDateTime;

/**
 * 使用mybatis时的实体基类
 *
 * @author changgg
 */
@Data
@ToString
public abstract class AbstractEntity implements BaseEntity {
    private static final long serialVersionUID = -4477750638713111299L;
    /**
     * 主键
     */
    @TableId(value = Constant.ID, type = IdType.AUTO)
    @ToString.Exclude
    private Long id;
    /**
     * 创建人
     */
    @TableField(value = Constant.CREATED_BY, fill = FieldFill.INSERT)
    private String createBy;
    /**
     * 更新人
     */
    @TableField(value = Constant.LAST_MODIFIED_BY, fill = FieldFill.INSERT_UPDATE)
    private String lastModifiedBy;
    /**
     * 创建时间
     */
    @TableField(value = Constant.CREATED_TIME, fill = FieldFill.INSERT)
    private LocalDateTime createTime;
    /**
     * 更新时间
     */
    @TableField(value = Constant.LAST_MODIFIED_TIME, fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime lastModifiedTime;
}
