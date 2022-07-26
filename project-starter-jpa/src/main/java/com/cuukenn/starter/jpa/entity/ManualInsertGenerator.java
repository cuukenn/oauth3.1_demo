package com.cuukenn.starter.jpa.entity;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentityGenerator;

import java.io.Serializable;

/**
 * 自定义的主键生成策略，如果填写了主键id，如果数据库中没有这条记录，则新增指定id的记录；否则更新记录
 * 如果不填写主键id，则利用数据库本身的自增策略指定id
 *
 * @author changgg
 */
public class ManualInsertGenerator extends IdentityGenerator {
    @Override
    public Serializable generate(SharedSessionContractImplementor s, Object obj) throws HibernateException {
        Serializable id = s.getEntityPersister(null, obj).getClassMetadata().getIdentifier(obj, s);
        if (id != null) {
            return id;
        } else {
            return super.generate(s, obj);
        }
    }
}
