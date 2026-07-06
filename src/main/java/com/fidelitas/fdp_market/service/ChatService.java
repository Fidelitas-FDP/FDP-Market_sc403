package com.fidelitas.fdp_market.service;

import com.fidelitas.fdp_market.domain.Mensaje;
import com.fidelitas.fdp_market.domain.Chat;
import com.fidelitas.fdp_market.domain.Usuario;
import com.fidelitas.fdp_market.repository.MensajeRepository;
import com.fidelitas.fdp_market.repository.ChatRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class ChatService {
    private final MensajeRepository mensajeRepository;
    private final ChatRepository chatRepository;
    
    

    public ChatService(MensajeRepository mensajeRepository, ChatRepository chatRepository) {
        this.mensajeRepository = mensajeRepository;
        this.chatRepository = chatRepository;
    }
      
    
    // hu18 - inserta mensajes desde orden
    @Transactional
    public Mensaje enviarMensaje(Long chatId, Usuario emisor, String contenido) {
        Chat chat = chatRepository.findById(chatId)
                .orElseThrow(() -> new IllegalArgumentException("Chat no valido"));

        Mensaje mensaje = new Mensaje();
        mensaje.setChat(chat);
        mensaje.setUsuarioEmisor(emisor);
        mensaje.setContenido(contenido);

        return mensajeRepository.save(mensaje);
    }

    @Transactional(readOnly = true)
    public List<Mensaje> listarHistorialChat(Long chatId) {
        return mensajeRepository.findByChatIdOrderByFechaAsc(chatId);
    }
}
