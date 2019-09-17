package com.controllers;

import com.models.Counter;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
public class ClickCounterController {

    @RequestMapping(
            value="/incrementCounter",
            method = POST
    )
    public void incrementCounter() {
        Counter.incrementCounter();
    }
}