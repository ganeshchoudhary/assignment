package com.learning.food.ordering.service.controller;


import com.learning.food.ordering.service.constant.ApiConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

/**
 * Health check rest controller
 */
@RestController
public class HealthCheckRestController {
    private final Logger logger;

    @Autowired
    public HealthCheckRestController() {
        this.logger = LoggerFactory.getLogger(HealthCheckRestController.class);
    }


    /**
     * Api to check health of application
     *
     * @return response body
     */
    @GetMapping(path = ApiConstant.HEALTH_CHECK_API, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity checkHealth() {
        logger.info("Health checking.");
        HashMap<String, String> map = new HashMap<>();
        map.put("status", "OK");
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

}
