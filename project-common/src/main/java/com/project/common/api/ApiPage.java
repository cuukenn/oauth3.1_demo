package com.project.common.api;

import com.project.common.poj.BaseDTO;
import lombok.AccessLevel;
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
@Builder(access = AccessLevel.MODULE)
public class ApiPage<T extends List<V>, V extends BaseDTO> implements Serializable {
    private static final long serialVersionUID = -3318767403465787461L;
    private Integer pageNumber;
    private Integer pageSize;
    private Integer total;
    private T list;
}
