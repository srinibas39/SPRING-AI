package com.example.demo.Service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class MultipleLLMChatService {

    private final ChatClient openAIChatClient;
    private final ChatClient geminiChatClient;

    public MultipleLLMChatService(
            @Qualifier("openAIChatClient") ChatClient openAIChatClient,
            @Qualifier("geminiChatClient") ChatClient geminiChatClient) {
        this.openAIChatClient = openAIChatClient;
        this.geminiChatClient = geminiChatClient;
    }

    public String chatOpenAI(String message) {
        return openAIChatClient.prompt(message).call().content();
    }

    public String chatGemini(String message) {
        return geminiChatClient.prompt(message).call().content();
    }

}
