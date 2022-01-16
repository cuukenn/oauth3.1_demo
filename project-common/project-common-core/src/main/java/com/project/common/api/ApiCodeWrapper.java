package com.project.common.api;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

/**
 * @author changgg
 */
@Data
@RequiredArgsConstructor
@ToString
public final class ApiCodeWrapper implements ApiCode {
    private final String code;
    private final String msg;
}
