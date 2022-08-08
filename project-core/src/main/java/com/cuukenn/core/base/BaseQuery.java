package com.cuukenn.core.base;

import java.io.Serializable;

/**
 * 作为query时的基类
 * 用于请求动作的API数据封装,不进行数据修改
 * 数据发生变更使用{@link BaseCommand}
 *
 * @author changgg
 */
public interface BaseQuery extends Serializable {
}
