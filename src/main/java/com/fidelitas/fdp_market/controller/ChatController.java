package com.fidelitas.fdp_market.controller;

import com.fidelitas.fdp_market.service.ChatService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/chat")
public class ChatController {
    
    private final ChatService chatService;

    public ChatController(ChatService chatService) {
        this.chatService = chatService;
    }
    
    // hu18 - sala de chat
    @GetMapping("/sala/{chatId}")
    public String abrirSala(@PathVariable Long chatId, Model model) {
        model.addAttribute("mensajes", chatService.listarHistorialChat(chatId));
        model.addAttribute("chatId", chatId);
        return "chat/sala_comunicacion";
    }
    
}
