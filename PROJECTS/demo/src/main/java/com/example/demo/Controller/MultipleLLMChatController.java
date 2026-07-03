package com.example.demo.Controller;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Service.MultipleLLMChatService;

@RestController
@RequestMapping("/api/multiple-llm-chat")
public class MultipleLLMChatController {

   private final ChatClient openAIChatClient;
   private final ChatClient geminiChatClient;

    public MultipleLLMChatController(ChatClient openAIChatClient, ChatClient geminiChatClient) {
        this.openAIChatClient = openAIChatClient;
        this.geminiChatClient = geminiChatClient;
    }

    @GetMapping("/chat/openai")
    public String chatOpenAI(@RequestParam String message) {
        return openAIChatClient.prompt(message).call().content();
    }

    @GetMapping("/chat/gemini")
    public String chatGemini(@RequestParam String message) {
        return geminiChatClient.prompt(message).call().content();
    }
}
