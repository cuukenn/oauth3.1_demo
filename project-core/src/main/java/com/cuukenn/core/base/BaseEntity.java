package com.cuukenn.core.base;

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
     * 主键列名
     */
    String ID = "id";
    /**
     * 创建时间
     */
    String CREATED_TIME = "create_time";
    /**
     * 创建者
     */
    String CREATED_BY = "create_by";
    /**
     * 最近修改时间
     */
    String LAST_MODIFIED_TIME = "last_modified_time";
    /**
     * 修改者
     */
    String LAST_MODIFIED_BY = "last_modified_by";

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
