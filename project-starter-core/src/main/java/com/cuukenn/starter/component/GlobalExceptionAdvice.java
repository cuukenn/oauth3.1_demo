package com.cuukenn.starter.component;

import com.cuukenn.core.api.ApiCodes;
import com.cuukenn.core.api.ApiResult;
import com.cuukenn.core.exception.ApiException;
import com.cuukenn.core.exception.BizException;
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
public class GlobalExceptionAdvice {
    @ExceptionHandler
    @ResponseStatus(HttpStatus.OK)
    public ApiResult<String> handle(ApiException exception) {
        log.error("API异常,code:[{}],msg:[{}],detail:[{}]", exception.getCode(), exception.getMessage(), exception.getDetail());
        return ApiResult.of(exception.getCode(), exception.getMessage());
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.OK)
    public ApiResult<Void> handle(BizException exception) {
        log.error("功能异常,code:[{}],msg:[{}]", ApiCodes.ERROR, exception.getMessage());
        return ApiResult.of(ApiCodes.ERROR, exception.getMessage());
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.OK)
    public ApiResult<Void> handle(ValidationException exception) {
        log.error("参数异常,code:[{}],msg:[{}]", ApiCodes.PARAM_ERROR, exception.getMessage());
        return ApiResult.of(ApiCodes.PARAM_ERROR);
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.OK)
    public ApiResult<Void> handle(Throwable exception) {
        log.error("系统异常", exception);
        return ApiResult.of(ApiCodes.ERROR);
    }
}
