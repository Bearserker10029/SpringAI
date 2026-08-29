package com.example.SpringAI.controller;

import com.example.SpringAI.service.AIService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/ai")
public class AIController {

    private final AIService aiService;

    public AIController(AIService aiService) {
        this.aiService = aiService;
    }

    @GetMapping("/ask")
    public ResponseEntity<String> ask(@RequestParam String ask){
        String result = aiService.ask(ask);
        return ResponseEntity.ok(result);
    }
}
