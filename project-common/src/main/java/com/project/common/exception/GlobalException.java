package com.project.common.exception;

import com.project.common.api.ApiCodes;
import com.project.common.api.ApiResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ValidationException;

/**
 * 全局异常
 *
 * @author changgg
 */
@RestControllerAdvice
@Slf4j
public class GlobalException {
    @ExceptionHandler
    @ResponseStatus(HttpStatus.OK)
    public ApiResult<Object> handle(ApiException exception) {
        log.error("api异常,code:[{}],msg:[{}]", exception.getCode(), exception.getMessage());
        return ApiResult.of(exception.getCode(), exception.getMessage());
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.OK)
    public ApiResult<Object> handle(BizException exception) {
        log.error("业务异常,code:[{}],msg:[{}]", ApiCodes.ERROR, exception.getMessage());
        return ApiResult.of(ApiCodes.ERROR);
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.OK)
    public ApiResult<Object> handle(ValidationException exception) {
        log.error("参数异常,code:[{}],msg:[{}]", ApiCodes.PARAM_ERROR, exception.getMessage());
        return ApiResult.of(ApiCodes.PARAM_ERROR);
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.OK)
    public ApiResult<Object> handle(Throwable exception) {
        log.error("系统异常", exception);
        return ApiResult.of(ApiCodes.ERROR);
    }
}
