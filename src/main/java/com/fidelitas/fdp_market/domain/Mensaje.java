package com.fidelitas.fdp_market.domain;

import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "mensaje")
public class Mensaje implements Serializable {
    
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_chat", nullable = false)
    private Chat chat;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_usuario_emisor", nullable = false)
    private Usuario usuarioEmisor;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String contenido;

    @Column(updatable = false)
    private LocalDateTime fecha;

    // hook para asignar hora previo a insert en BD, evidanto boilerplate
    @PrePersist
    protected void onCreate() {
        if (this.fecha == null) this.fecha = LocalDateTime.now();
    }

    public Mensaje() {
    }

    public Mensaje(Long id, Chat chat, Usuario usuarioEmisor, String contenido, LocalDateTime fecha) {
        this.id = id;
        this.chat = chat;
        this.usuarioEmisor = usuarioEmisor;
        this.contenido = contenido;
        this.fecha = fecha;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Chat getChat() {
        return chat;
    }

    public void setChat(Chat chat) {
        this.chat = chat;
    }

    public Usuario getUsuarioEmisor() {
        return usuarioEmisor;
    }

    public void setUsuarioEmisor(Usuario usuarioEmisor) {
        this.usuarioEmisor = usuarioEmisor;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }
    
    
}
