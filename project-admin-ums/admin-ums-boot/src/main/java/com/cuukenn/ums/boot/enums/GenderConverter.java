package com.cuukenn.ums.boot.enums;

import com.cuukenn.core.exception.BizException;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 * @author changgg
 */
@Converter
public class GenderConverter implements AttributeConverter<GenderEnum, Integer> {
    @Override
    public Integer convertToDatabaseColumn(GenderEnum attribute) {
        if (attribute == null) {
            throw new BizException("非法的性别 : null");
        }
        return attribute.getValue();
    }

    @Override
    public GenderEnum convertToEntityAttribute(Integer dbData) {
        for (GenderEnum s : GenderEnum.values()) {
            if (s.getValue().equals(dbData)) {
                return s;
            }
        }
        throw new BizException("非法的性别 : " + dbData);
    }
}
