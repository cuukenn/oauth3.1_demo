package com.project.ums.server.web;

import com.project.security.annotation.GetAnonymousAccess;
import com.project.starter.config.BuildInfoProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author changgg
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/")
public class ApplicationController {
    private final BuildInfoProperties properties;

    @GetMapping(value = "/")
    @GetAnonymousAccess
    public String hello() {
        return String.format("the backend service[%s] is running on the port[%s]", properties.getAppName(), properties.getAppVersion());
    }
}
