package com.vntechies.awscloudwatchagentforawsxray.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {

    private final static Logger LOGGER = LoggerFactory.getLogger(IndexController.class);

    @GetMapping(path = "/healthcheck")
    public ResponseEntity<?> checkHealthy() {
        LOGGER.info("[checkHealthy] -> Executed!");
        return new ResponseEntity<>("Hello VNTechies! I'm Ok", HttpStatus.OK);
    }
}
