package com.vntechies.awscloudwatchagentforawsxray.controllers;

import com.vntechies.awscloudwatchagentforawsxray.entities.SongEntity;
import com.vntechies.awscloudwatchagentforawsxray.services.ISongService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/dynamodb")
public class DynamoDBController {

    private static final Logger LOGGER = LoggerFactory.getLogger(DynamoDBController.class);

    @Autowired
    private ISongService iSongService;

    @PostMapping(path = "/addItem")
    public ResponseEntity<?> addItem(@RequestBody SongEntity songEntity) {
        LOGGER.info("[addItem] -> Executed!");
        return new ResponseEntity<>(iSongService.add(songEntity), HttpStatus.CREATED);
    }
}
