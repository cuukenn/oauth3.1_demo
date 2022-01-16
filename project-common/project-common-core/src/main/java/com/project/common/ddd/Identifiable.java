package com.project.common.ddd;

/**
 * @author changgg
 */
public interface Identifiable<ID extends Identifier> {
    /**
     * 实体标识位
     *
     * @return 标识位
     */
    ID getId();
}
