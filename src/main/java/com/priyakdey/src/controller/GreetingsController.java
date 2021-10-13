package com.priyakdey.src.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

/**
 * Request Handler class which helps in greeting users
 *
 * @author Priyak Dey
 */
@RestController
@RequestMapping(path = "/greetings", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
public class GreetingsController {

    @GetMapping
    public ResponseEntity<String> greet() {
        return ResponseEntity.ok("Howdy stranger, how are you doing today?");
    }

}
