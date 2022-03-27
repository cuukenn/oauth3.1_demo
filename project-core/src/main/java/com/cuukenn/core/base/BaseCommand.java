package com.cuukenn.core.base;

import java.io.Serializable;

/**
 * 作为Command命令的基类
 * 用于请求进行相关更改的命令封装
 * 数据未发生变更使用{@link BaseQuery}
 *
 * @author changgg
 */
public interface BaseCommand extends Serializable {
}
