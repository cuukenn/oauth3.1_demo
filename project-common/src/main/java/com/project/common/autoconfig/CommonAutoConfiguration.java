package com.project.common.autoconfig;

import cn.hutool.extra.spring.EnableSpringUtil;
import com.project.common.config.BuildInfoProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author changgg
 */
@Configuration
@EnableConfigurationProperties(BuildInfoProperties.class)
@EnableSpringUtil
public class CommonAutoConfiguration {
}
