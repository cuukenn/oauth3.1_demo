package com.cuukenn.core.validate;

import cn.hutool.core.util.StrUtil;
import com.cuukenn.core.util.RegexUtil;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * 校验手机号
 *
 * @author changgg
 */
public class TelephoneValidator implements ConstraintValidator<Telephone, String> {
    private boolean require = false;

    @Override
    public void initialize(Telephone constraintAnnotation) {
        require = constraintAnnotation.required();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (StrUtil.isEmpty(value)) {
            return !require;
        } else {
            return RegexUtil.MOBILE_PATTERN.matcher(value).matches();
        }
    }
}
