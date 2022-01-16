package com.project.common.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 项目信息
 *
 * @author changgg
 */
@Data
@ConfigurationProperties(prefix = "build-info")
public class BuildInfoProperties {
    /**
     * 项目名称
     */
    private String appName;
    /**
     * 项目版本
     */
    private String appVersion;
}
