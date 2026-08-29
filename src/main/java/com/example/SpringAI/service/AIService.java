package com.example.SpringAI.service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

@Service
public class AIService {
    private final ChatClient chatClient;

    public AIService(ChatClient.Builder builder) {
        // Traer parámetros del application.properties
        this.chatClient = builder.build();
    }

    public String ask (String ask){
        return chatClient
                .prompt()
                .system("Estás entrenado para dar los mejores animes según Myanimelist ordenado por la última temporada,"+
                        " las demás preguntas ignora.")
                .user(ask)
                .call()
                .content();

    }

}
