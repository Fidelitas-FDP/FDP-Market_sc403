package com.fidelitas.fdp_market.domain;

import jakarta.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "inventario_serial")
public class InventarioSerial implements Serializable {
    
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_anuncio", nullable = false)
    private Anuncio anuncio;

    @Column(name = "detalle_credencial", nullable = false, length = 255)
    private String detalleCredencial;

    @Column(name = "id_orden_asignada")
    private Long idOrdenAsignada; // long = X dependencias circulares X Orden

    public InventarioSerial() {
    }

    public InventarioSerial(Long id, Anuncio anuncio, String detalleCredencial, Long idOrdenAsignada) {
        this.id = id;
        this.anuncio = anuncio;
        this.detalleCredencial = detalleCredencial;
        this.idOrdenAsignada = idOrdenAsignada;
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

    public String getDetalleCredencial() {
        return detalleCredencial;
    }

    public void setDetalleCredencial(String detalleCredencial) {
        this.detalleCredencial = detalleCredencial;
    }

    public Long getIdOrdenAsignada() {
        return idOrdenAsignada;
    }

    public void setIdOrdenAsignada(Long idOrdenAsignada) {
        this.idOrdenAsignada = idOrdenAsignada;
    }
    
    
}
