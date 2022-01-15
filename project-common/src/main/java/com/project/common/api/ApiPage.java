package com.project.common.api;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * 分页数据模型
 *
 * @author changgg
 */
@Data
@Builder
public class ApiPage<T> implements Serializable {
    private static final long serialVersionUID = -3318767403465787461L;
    private Integer pageNumber;
    private Integer pageSize;
    private Integer total;
    private T list;
}
