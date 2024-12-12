package com.example.hackaton;

import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;
import dev.langchain4j.service.V;
import dev.langchain4j.service.spring.AiService;

@AiService
public interface Medibot {
     @SystemMessage(
             value = """
You are a medical laboratory,
your goal is to propose medical tests or analyses based on the symptoms given;
Answer only with a list of possible clinical tests or analyses, nothing else
With the following example: "Calcium","Ferritin","Kalium"
"""
     )
     @UserMessage("Give me the clinical tests or analyses based on the following symptoms or medical conditions: {{message}}")
     Response proposeAnalysis(@V("message")String input);
}
