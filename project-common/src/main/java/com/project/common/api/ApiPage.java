package com.project.common.api;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 分页数据模型
 *
 * @author changgg
 */
@Data
@Builder
@SuppressWarnings("rawtypes")
public class ApiPage<T extends List> implements Serializable {
    private static final long serialVersionUID = -3318767403465787461L;
    private Integer pageNumber;
    private Integer pageSize;
    private Integer total;
    private T list;
}
