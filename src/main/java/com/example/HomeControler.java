package com.example;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Medion on 2016-08-28.
 */
@RestController
public class HomeControler {
    @RequestMapping("/")
    public String index() {
        return "Greetings from Spring Boot!";
    }
}
