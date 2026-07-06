package com.fidelitas.fdp_market.domain;

import java.io.Serializable;
import java.util.Objects;


public class ChatParticipanteId implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long idChat;
    private Long idUsuario;

    public ChatParticipanteId() {}

    public ChatParticipanteId(Long idChat, Long idUsuario) {
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

    // regla basica de igualdad, obligatorio por llave compuesta
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChatParticipanteId that = (ChatParticipanteId) o;
        return Objects.equals(idChat, that.idChat) && Objects.equals(idUsuario, that.idUsuario);
    }

    // genera hash en campos especificados para que collections pueda indexar registro
    @Override
    public int hashCode() {
        return Objects.hash(idChat, idUsuario);
    }
}
