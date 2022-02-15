package com.project.starter.autoconfig;

import cn.hutool.extra.spring.EnableSpringUtil;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;
import com.project.core.util.DateUtil;
import com.project.starter.component.ApplicationController;
import com.project.starter.config.BuildInfoProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;

/**
 * @author changgg
 */
@Configuration
@EnableConfigurationProperties(BuildInfoProperties.class)
@EnableSpringUtil
public class CommonAutoConfiguration {
    /**
     * 支持jdk8的各种时间类型的json序列化
     *
     * @return bean
     */
    @Bean
    @ConditionalOnClass({ObjectMapper.class, Jackson2ObjectMapperBuilderCustomizer.class})
    @ConditionalOnMissingBean(Jackson2ObjectMapperBuilderCustomizer.class)
    public Jackson2ObjectMapperBuilderCustomizer jackson2ObjectMapperBuilderCustomizer() {
        Map<Class<?>, JsonSerializer<?>> serializers = new HashMap<>(3);
        serializers.put(LocalDateTime.class, new LocalDateTimeSerializer(DateUtil.PATTERN1));
        serializers.put(LocalDate.class, new LocalDateSerializer(DateUtil.PATTERN5));
        serializers.put(LocalTime.class, new LocalTimeSerializer(DateUtil.PATTERN8));

        Map<Class<?>, JsonDeserializer<?>> deserializers = new HashMap<>(3);
        deserializers.put(LocalDateTime.class, new LocalDateTimeDeserializer(DateUtil.PATTERN1));
        deserializers.put(LocalDate.class, new LocalDateDeserializer(DateUtil.PATTERN5));
        deserializers.put(LocalTime.class, new LocalTimeDeserializer(DateUtil.PATTERN8));
        return builder -> builder.serializersByType(serializers).deserializersByType(deserializers);
    }

    /**
     * 注册默认的/处理器
     *
     * @param buildInfoProperties 项目信息
     * @param serverProperties    服务器信息
     * @return api
     */
    @Bean
    public ApplicationController applicationController(BuildInfoProperties buildInfoProperties, ServerProperties serverProperties) {
        return new ApplicationController(buildInfoProperties, serverProperties);
    }
}
