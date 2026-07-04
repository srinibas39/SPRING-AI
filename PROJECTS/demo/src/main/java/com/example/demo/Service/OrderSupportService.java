package com.example.demo.Service;

import java.time.LocalDateTime;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.template.st.StTemplateRenderer;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

@Service
public class OrderSupportService {

    private final ChatClient chatClient;

    @Value("classpath:prompt/system-prompt.st")
    private Resource systemPrompt;

    @Value("classpath:prompt/user-prompt.st")
    private Resource userPrompt;

    public OrderSupportService(@Qualifier("openAIChatClient") ChatClient chatClient) {
        this.chatClient = chatClient;
    }

    public ChatResponse chat(String customerName, String userId, String message) {
        String orderHistory = "- Order #1024: Shipped (Tracking: 1Z999), Delivery expected July 6.\n- Order #1011: Delivered June 20.";
        String policies = "- Returns are allowed within 30 days of delivery.\n- Orders cannot be canceled once status is 'Shipped'.";
        return chatClient.prompt()
                .templateRenderer(StTemplateRenderer.builder()
                        .startDelimiterToken('<')
                        .endDelimiterToken('>')
                        .build())
                .system(sp -> sp.text(systemPrompt)
                        .param("customerName", customerName)
                        .param("customerTier", "VIP")
                        .param("currentDateTime", LocalDateTime.now().toString())
                        .param("orderHistoryData", orderHistory)
                        .param("applicablePolicies", policies))
                .user(u -> u.text(userPrompt)
                        .param("currentUiScreen", "Order Tracking Page")
                        .param("chatHistorySummary", "User asked about delivery date previously.")
                        .param("userMessage", message))
                .call()
                .chatResponse();
    }

}
