package com.fidelitas.fdp_market.domain;

import jakarta.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "imagen_anuncio")
public class ImagenAnuncio implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_anuncio", nullable = false)
    private Anuncio anuncio;

    @Column(nullable = false, length = 255)
    private String url;

    public ImagenAnuncio() {
    }

    public ImagenAnuncio(Long id, Anuncio anuncio, String url) {
        this.id = id;
        this.anuncio = anuncio;
        this.url = url;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Anuncio getAnuncio() {
        return anuncio;
    }

    public void setAnuncio(Anuncio anuncio) {
        this.anuncio = anuncio;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    
}
