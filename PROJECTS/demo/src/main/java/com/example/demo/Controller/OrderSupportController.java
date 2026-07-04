package com.example.demo.Controller;

import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Service.OrderSupportService;

@RestController
@RequestMapping("/api/order-support")
public class OrderSupportController {

    private final OrderSupportService orderSupportService;

    public OrderSupportController(OrderSupportService orderSupportService) {
        this.orderSupportService = orderSupportService;
    }

    @GetMapping("/chat")
    public ResponseEntity<ChatResponse> chat(@RequestParam("customerName") String customerName, @RequestParam("userId") String userId, @RequestParam("message") String message) {
        return ResponseEntity.ok(orderSupportService.chat(customerName, userId, message));
    }

}
