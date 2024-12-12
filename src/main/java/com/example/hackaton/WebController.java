package com.example.hackaton;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WebController {

    @Autowired
    private Medibot medibot;

    @GetMapping
    public String getChat(){
        return medibot.proposeAnalysis("What is the biggest state in the USA");
    }
}
