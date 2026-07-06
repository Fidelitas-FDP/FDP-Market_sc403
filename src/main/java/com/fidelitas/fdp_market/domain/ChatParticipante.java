package com.fidelitas.fdp_market.domain;

import jakarta.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "chat_participante")
@IdClass(ChatParticipanteId.class)
public class ChatParticipante implements Serializable {
    
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id_chat")
    private Long idChat;

    @Id
    @Column(name = "id_usuario")
    private Long idUsuario;

    public ChatParticipante() {
    }

    public ChatParticipante(Long idChat, Long idUsuario) {
        this.idChat = idChat;
        this.idUsuario = idUsuario;
    }

    public Long getIdChat() {
        return idChat;
    }

    public void setIdChat(Long idChat) {
        this.idChat = idChat;
    }

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }
    
    
    
}
