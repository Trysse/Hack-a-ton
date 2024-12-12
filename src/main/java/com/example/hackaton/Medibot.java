package com.example.hackaton;

import dev.langchain4j.service.spring.AiService;

@AiService
public interface Medibot {
     String proposeAnalysis(String input);
}
