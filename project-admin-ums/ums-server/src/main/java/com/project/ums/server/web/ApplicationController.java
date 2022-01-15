package com.project.ums.server.web;

import com.project.security.annotation.GetAnonymousAccess;
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
    @GetMapping(value = "/")
    @GetAnonymousAccess
    public String hello() {
        return "the backend service is running";
    }
}
