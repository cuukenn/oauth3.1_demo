package com.cuukenn.core.api;

import com.cuukenn.core.base.BaseDTO;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 分页数据模型
 * 由{@link ApiResult}的静态方法进行初始化、构造方法不对外开放(反序列化需要提供无参构造函数,禁止直接调用)
 *
 * @author changgg
 * @see ApiResult
 */
@Data
@Builder(access = AccessLevel.PACKAGE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
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
