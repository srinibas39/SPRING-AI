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

   private final MultipleLLMChatService multipleLLMChatService;

    public MultipleLLMChatController(MultipleLLMChatService multipleLLMChatService) {
        this.multipleLLMChatService = multipleLLMChatService;
    }

    @GetMapping
    public String chat(@RequestParam String message) {
        return multipleLLMChatService.chat(message);
    }

    @GetMapping("/chat/openai")
    public String chatOpenAI(@RequestParam String message) {
        return multipleLLMChatService.chatOpenAI(message);
    }

    @GetMapping("/chat/gemini")
    public String chatGemini(@RequestParam String message) {
        return multipleLLMChatService.chatGemini(message);
    }
}
