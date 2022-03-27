package com.cuukenn.starter.component;

import cn.hutool.extra.spring.SpringUtil;
import liquibase.integration.spring.SpringLiquibase;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

/**
 * 启动时移除Liquibase锁
 *
 * @author changgg
 */
@Slf4j
@SuppressWarnings("NullableProblems")
public class LiquibaseLockRemovePostProcessor implements BeanPostProcessor {
    @SuppressWarnings({"SqlResolve", "SqlNoDataSourceInspection"})
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof SpringLiquibase) {
            try {
                DataSource dataSource = SpringUtil.getBean(DataSource.class);
                JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
                int flag = jdbcTemplate.update("update databasechangeloglock set locked=false where locked=true");
                log.info("delete liquibase lock result:[{}]", flag);
            } catch (RuntimeException e) {
                log.error("delete liquibase lock failed", e);
            }
        }
        return bean;
    }
}
