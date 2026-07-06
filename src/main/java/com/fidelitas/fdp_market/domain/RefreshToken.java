package com.fidelitas.fdp_market.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.time.Instant;


@Entity
@Table(name = "refresh_token")
public class RefreshToken {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "usuario_id", referencedColumnName = "id")
    private Usuario usuario;

    @Column(nullable = false, unique = true)
    private String token;

    @Column(nullable = false)
    private Instant fechaExpiracion;
    
    // getters y setters    
    public Usuario getUsuario() { 
        return usuario; 
    }
    
    public void setUsuario(Usuario usuario) { 
        this.usuario = usuario; 
    }

    public String getToken() { 
        return token; 
    }
    
    public void setToken(String token) { 
        this.token = token; 
    }

    public Instant getFechaExpiracion() { 
        return fechaExpiracion; 
    }
    
    public void setFechaExpiracion(Instant fechaExpiracion) { 
        this.fechaExpiracion = fechaExpiracion; 
    }
}