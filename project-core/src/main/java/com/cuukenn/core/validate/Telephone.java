package com.cuukenn.core.validate;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 校验手机号是否合规
 *
 * @author changgg
 */
@Target(value = {ElementType.FIELD})
@Retention(value = RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = TelephoneValidator.class)
public @interface Telephone {
    boolean required() default false;

    String message() default "{手机号格式不正确}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
