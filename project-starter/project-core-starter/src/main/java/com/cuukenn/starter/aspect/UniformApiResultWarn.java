package com.cuukenn.starter.aspect;

import com.cuukenn.core.api.ApiResult;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.http.ResponseEntity;

/**
 * 用于提醒使用统一API管理
 *
 * @author changgg
 */
@Aspect
@Slf4j
public class UniformApiResultWarn {

    @Pointcut("@annotation(org.springframework.web.bind.annotation.GetMapping) "
            + "|| @annotation(org.springframework.web.bind.annotation.PostMapping)"
            + "|| @annotation(org.springframework.web.bind.annotation.DeleteMapping)"
    )
    public void pointcut() {
    }

    @Around("pointcut()")
    public Object afterReturning(ProceedingJoinPoint point) throws Throwable {
        Object ret = point.proceed();
        if (ret != null) {
            //返回值不是ApiResult或者ResponseEntity<ApiResult>时打印警告日志
            boolean flag = ret instanceof ApiResult;
            if (!flag) {
                if (ret instanceof ResponseEntity) {
                    Object body = ((ResponseEntity<?>) ret).getBody();
                    flag = body instanceof ApiResult || body == null;
                }
            }
            if (!flag) {
                //拦截的实体类
                Object target = point.getTarget();
                //拦截的方法名称
                String methodName = point.getSignature().getName();
                log.warn("API[{}.{}]未使用统一返回值[{}]包装,请进行修改", target.getClass().getName(), methodName, ApiResult.class.getName());
            }
        }
        return ret;
    }
}
