package com.project.core.api;

import com.project.core.base.BaseDTO;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * 分页数据模型
 * 由{@link ApiResult}的静态方法进行初始化、构造方法不对外开放
 *
 * @author changgg
 * @see ApiResult
 */
@Data
@Builder(access = AccessLevel.MODULE)
public class ApiPage<V extends BaseDTO> implements BaseDTO {
    private static final long serialVersionUID = -3318767403465787461L;
    /**
     * 分页页码
     */
    private Integer pageNumber;
    /**
     * 分页大小
     */
    private Integer pageSize;
    /**
     * 总数据量
     */
    private Long total;
    /**
     * 当前分页数据
     */
    private List<V> list;
}
