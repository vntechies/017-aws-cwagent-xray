package com.vntechies.awscloudwatchagentforawsxray.controllers;

import com.vntechies.awscloudwatchagentforawsxray.services.IObjectService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping(path = "/api/s3")
public class S3Controller {

    private static final Logger LOGGER = LoggerFactory.getLogger(S3Controller.class);

    @Autowired
    private IObjectService iObjectService;

    @PostMapping(path = "/putObject")
    public ResponseEntity<?> putObject(@RequestParam("file") MultipartFile file,
            @RequestParam("fileName") String fileName) {
        LOGGER.info("[putObject] -> Executed");
        try {
            byte[] bytes = file.getBytes();
            iObjectService.putObject(file.getOriginalFilename(), bytes);
            LOGGER.info("Put an new object successfully");
            return new ResponseEntity<>("Put an new object successfully", HttpStatus.OK);
        } catch (Exception e) {
            LOGGER.error("Put an new object failed");
            LOGGER.error(e.getMessage());
        }
        return new ResponseEntity<>("Put an new object failed", HttpStatus.BAD_REQUEST);
    }
}
