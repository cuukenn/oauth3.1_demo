package com.project.starter.component;

import cn.hutool.core.date.BetweenFormatter;
import cn.hutool.core.date.DateUtil;
import com.project.core.api.ApiResult;
import com.project.core.base.BaseDTO;
import com.project.starter.config.BuildInfoProperties;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * @author changgg
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/")
public class ApplicationController {
    private final BuildInfoProperties buildInfoProperties;
    private final ServerProperties serverProperties;
    /**
     * 启动时间
     */
    private final Date uptime = new Date();

    /**
     * 为/路径提供默认的返回信息
     *
     * @return info
     */
    @GetMapping(value = "/")
    public ApiResult<AppInfo> hello() {
        return ApiResult.success(new AppInfo(buildInfoProperties.getAppName(), buildInfoProperties.getAppVersion(), serverProperties.getPort(), uptime));
    }

    @Data
    static class AppInfo implements BaseDTO {
        private static final long serialVersionUID = -8484775484911938758L;
        /**
         * 程序名称
         */
        private final String name;
        /**
         * 程序版本
         */
        private final String version;
        /**
         * 程序运行端口
         */
        private final Integer port;
        /**
         * 程序运行时间
         */
        private final String runTime;

        AppInfo(String name, String version, Integer port, Date uptime) {
            this.name = name;
            this.version = version;
            this.port = port;
            this.runTime = DateUtil.formatBetween(uptime, new Date(), BetweenFormatter.Level.MINUTE);
        }
    }
}
