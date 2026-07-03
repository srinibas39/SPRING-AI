package com.example.demo.Configuration;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.google.genai.GoogleGenAiChatModel;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AIConfig {

    @Bean("openAIChatClient")
    public ChatClient openAIChatClient(OpenAiChatModel openAiChatModel) {
        return ChatClient.builder(openAiChatModel)
                .defaultSystem("""
                        You are an insurance agent.
                        You are only job is to give answer to queries of clients about insurance.
                        If the query is not about insurance, you will respond with "I don't know".
                        """)
                .build();
    }

    @Bean("geminiChatClient")
    public ChatClient geminiChatClient(GoogleGenAiChatModel geminiChatModel) {
        return ChatClient.builder(geminiChatModel).build();
    }
}
