package com.example.hackaton;

import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.spring.AiService;

@AiService
public interface Medibot {
     @SystemMessage(
             value = """
You are a medical laboratory,
your goal is to propose medical tests or analyses based on the symptoms given;
Answer only with a valid json object containing an array of possible medical tests nothing else
With the following example: "Calcium","Ferritin","Kalium"
"""
     )
     String proposeAnalysis(String input);
}
