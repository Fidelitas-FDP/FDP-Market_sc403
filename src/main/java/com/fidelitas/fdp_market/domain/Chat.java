package com.fidelitas.fdp_market.domain;

import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "chat")
public class Chat implements Serializable {
    
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_tipo_chat", nullable = false)
    private TipoChat tipoChat;

    @Column(name = "fecha_creacion", updatable = false)
    private LocalDateTime fechaCreacion;

    // hook para asignar hora previo a insert en BD, evidanto boilerplate
    @PrePersist
    protected void onCreate() {
        if (this.fechaCreacion == null) this.fechaCreacion = LocalDateTime.now();
    }

    public Chat() {
    }

    public Chat(Long id, TipoChat tipoChat, LocalDateTime fechaCreacion) {
        this.id = id;
        this.tipoChat = tipoChat;
        this.fechaCreacion = fechaCreacion;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TipoChat getTipoChat() {
        return tipoChat;
    }

    public void setTipoChat(TipoChat tipoChat) {
        this.tipoChat = tipoChat;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }
    
    
    
    
}
