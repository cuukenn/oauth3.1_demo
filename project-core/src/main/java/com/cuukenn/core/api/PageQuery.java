package com.cuukenn.core.api;

import cn.hutool.core.util.StrUtil;
import com.cuukenn.core.base.BaseQuery;
import com.cuukenn.core.exception.BizException;
import com.cuukenn.core.util.Assert;
import com.cuukenn.core.util.RegexUtil;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 分页查询包装
 *
 * @author changgg
 */
@Data
public class PageQuery implements BaseQuery {
    private static final long serialVersionUID = 2194044269111688004L;
    private static final String DESC_STRING = "desc";
    /**
     * 分页页码
     */
    @Min(value = 1, message = "分页页码最小为1")
    @NotNull(message = "分页页码必须存在")
    private Integer pageNumber;
    /**
     * 分页大小
     */
    @Min(value = 1, message = "分页大小最小为1")
    @Max(value = 40, message = "分页大小最大为40")
    private Integer pageSize = 20;
    /**
     * 过滤条件
     */
    private String filter;
    /**
     * 分页排序
     */
    private String pageOrder;

    /**
     * 根据规则组装分页列
     *
     * @return 分页列
     */
    public List<ColumnOrder> transformColumnOrder() {
        if (StrUtil.isBlank(pageOrder)) {
            return Collections.emptyList();
        }
        String[] columnOrders = RegexUtil.DOT.split(pageOrder);
        List<ColumnOrder> result = new ArrayList<>(columnOrders.length);
        for (String columnOrder : columnOrders) {
            String[] split = RegexUtil.BLANK.split(columnOrder);
            Assert.isTrue(split.length >= 1 && split.length <= 2, () -> new BizException("排序字段规则错误," + pageOrder));
            result.add(new ColumnOrder(split[0], split.length == 1 || !DESC_STRING.equalsIgnoreCase(split[1])));
        }
        return result;
    }

    @RequiredArgsConstructor
    @Data
    public static class ColumnOrder {
        /**
         * 排序列名
         */
        private final String column;
        /**
         * 是否正序排序
         * true:asc,false:desc
         */
        private final boolean asc;
    }
}
