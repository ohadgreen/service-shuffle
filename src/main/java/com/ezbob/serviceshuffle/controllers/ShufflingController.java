package com.ezbob.serviceshuffle.controllers;

import com.ezbob.serviceshuffle.model.ShuffleRequest;
import com.ezbob.serviceshuffle.services.NumbersShuffle;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/")
public class ShufflingController {

    private final NumbersShuffle numbersShuffle;

    @Value("${logger.uri}")
    private String loggerUri;
    @Value("${shuffle.limit}")
    private int shuffleLimit;

    public ShufflingController(NumbersShuffle numbersShuffle) {
        this.numbersShuffle = numbersShuffle;
    }

    @PostMapping("shuffle")
    public ResponseEntity<List<Integer>> shuffleRequestedNumbers(@RequestBody ShuffleRequest shuffleRequest) {
        try {
            new RestTemplate().postForObject(loggerUri, shuffleRequest, ShuffleRequest.class);
        } catch (Exception e) {
            log.error("Logger service error {}", e.getMessage());
        }

        int requestedNumber = shuffleRequest.getRequestedNumber();
        if (requestedNumber >= 1 && requestedNumber <= shuffleLimit) {
            List<Integer> integerList = numbersShuffle.shuffleNumberArray(requestedNumber);
            return new ResponseEntity<>(integerList, HttpStatus.OK);
        } else {
            log.error("shuffle request value not acceptable: {}", requestedNumber);
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }
}
