package com.weave.assignment.backendtakehomehvnuif.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthCheckController {
    @GetMapping(value = "/ping")
    public String ping() {
        return "Service is up";
    }
}
