package com.example.hackaton;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WebController {

    @Autowired
    private Medibot medibot;

    @PostMapping("/submit")
    public void getChat(@RequestParam String prompt/* Patientdata zogezegd*/){
        Response answer = medibot.proposeAnalysis(prompt);
        System.out.println(answer);
    }
}
