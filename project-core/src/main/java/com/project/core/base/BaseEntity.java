package com.project.core.base;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 实体基类
 * 视具体orm框架使用特定的抽象类
 *
 * @author changgg
 */
public interface BaseEntity extends Serializable {
    /**
     * 实体主键
     *
     * @return 主键
     */
    Long getId();

    /**
     * 获取创建人
     *
     * @return 人员
     */
    String getCreateBy();

    /**
     * 获取最近更新人
     *
     * @return 人员
     */
    String getLastModifiedBy();

    /**
     * 创建时间
     *
     * @return 时间
     */
    LocalDateTime getCreateTime();

    /**
     * 更新时间
     *
     * @return 时间
     */
    LocalDateTime getLastModifiedTime();
}
