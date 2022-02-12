package com.project.starter.autoconfig;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.BlockAttackInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.IllegalSQLInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import com.project.core.constant.Constant;
import com.project.security.util.SecurityUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.time.LocalDateTime;

/**
 * mybatis-plus相关配置
 *
 * @author changgg
 */
@Configuration
@Slf4j
public class MyBatisPlusAutoConfiguration {
    /**
     * 防止恶意全表更新和删除
     *
     * @return 插件
     */
    @Bean
    public BlockAttackInnerInterceptor blockAttackInnerInterceptor() {
        return new BlockAttackInnerInterceptor();
    }

    /**
     * 分页
     *
     * @return 插件
     */
    @Bean
    @ConditionalOnMissingBean(MybatisPlusInterceptor.class)
    public MybatisPlusInterceptor paginationInnerInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));
        return interceptor;
    }

    /**
     * 创建时间、更新时间自动填充
     *
     * @return 时间元数据自动填充
     */
    @Bean
    @ConditionalOnMissingBean(MetaObjectHandler.class)
    @ConditionalOnMissingClass("com.project.security.util.SecurityUtils")
    public MetaObjectHandler timeMetaObjectWithoutSecurity() {
        log.info("register mataObjectHandler without security");
        return new MetaObjectHandler() {
            @Override
            public void insertFill(MetaObject metaObject) {
                LocalDateTime now = LocalDateTime.now();
                this.setFieldValByName(Constant.CREATED_TIME, now, metaObject);
                this.setFieldValByName(Constant.LAST_MODIFIED_TIME, now, metaObject);
                this.setFieldValByName(Constant.LAST_MODIFIED_BY, Constant.ANONYMOUS, metaObject);
            }

            @Override
            public void updateFill(MetaObject metaObject) {
                this.setFieldValByName(Constant.LAST_MODIFIED_TIME, LocalDateTime.now(), metaObject);
                this.setFieldValByName(Constant.LAST_MODIFIED_BY, Constant.ANONYMOUS, metaObject);
            }
        };
    }

    /**
     * 创建时间、更新时间自动填充
     *
     * @return 时间元数据自动填充
     */
    @Bean
    @ConditionalOnMissingBean(MetaObjectHandler.class)
    @ConditionalOnClass(SecurityUtils.class)
    public MetaObjectHandler timeMetaObjectInSecurity() {
        log.info("register mataObjectHandler in security");
        return new MetaObjectHandler() {
            @Override
            public void insertFill(MetaObject metaObject) {
                LocalDateTime now = LocalDateTime.now();
                this.setFieldValByName(Constant.CREATED_TIME, now, metaObject);
                this.setFieldValByName(Constant.LAST_MODIFIED_TIME, now, metaObject);
                this.setFieldValByName(Constant.CREATED_BY, SecurityUtils.hasAuthentication() ? SecurityUtils.getCurrentUsername() : Constant.ANONYMOUS, metaObject);
            }

            @Override
            public void updateFill(MetaObject metaObject) {
                this.setFieldValByName(Constant.LAST_MODIFIED_TIME, LocalDateTime.now(), metaObject);
                this.setFieldValByName(Constant.LAST_MODIFIED_BY, SecurityUtils.hasAuthentication() ? SecurityUtils.getCurrentUsername() : Constant.ANONYMOUS, metaObject);
            }
        };
    }

    /**
     * sql性能规范
     *
     * @return 插件
     */
    @Bean
    @Profile("dev")
    public IllegalSQLInnerInterceptor innerInterceptor() {
        return new IllegalSQLInnerInterceptor();
    }
}
