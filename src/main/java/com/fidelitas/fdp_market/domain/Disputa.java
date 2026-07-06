package com.fidelitas.fdp_market.domain;

import jakarta.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;


@Entity
@Table(name = "disputa")
public class Disputa implements Serializable {
    
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_orden", nullable = false, unique = true)
    private Orden orden;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_moderador")
    private Usuario moderador;

    @Column(name = "resolucion_tipo", length = 50)
    private String resolucionTipo;

    @Column(name = "monto_reembolsado")
    private BigDecimal montoReembolsado;

    @Column(name = "justificacion_texto", columnDefinition = "TEXT")
    private String justificacionTexto;

    @Column(name = "fecha_apertura", updatable = false)
    private LocalDateTime fechaApertura;

    @Column(name = "fecha_cierre")
    private LocalDateTime fechaCierre;

    // hook para asignar hora previo a insert en BD, evidanto boilerplate
    @PrePersist
    protected void onCreate() {
        if (this.fechaApertura == null) {
            this.fechaApertura = LocalDateTime.now();
        }
    }

    public Disputa() {
    }

    public Disputa(Long id, Orden orden, Usuario moderador, String resolucionTipo, BigDecimal montoReembolsado, String justificacionTexto, LocalDateTime fechaApertura, LocalDateTime fechaCierre) {
        this.id = id;
        this.orden = orden;
        this.moderador = moderador;
        this.resolucionTipo = resolucionTipo;
        this.montoReembolsado = montoReembolsado;
        this.justificacionTexto = justificacionTexto;
        this.fechaApertura = fechaApertura;
        this.fechaCierre = fechaCierre;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Orden getOrden() {
        return orden;
    }

    public void setOrden(Orden orden) {
        this.orden = orden;
    }

    public Usuario getModerador() {
        return moderador;
    }

    public void setModerador(Usuario moderador) {
        this.moderador = moderador;
    }

    public String getResolucionTipo() {
        return resolucionTipo;
    }

    public void setResolucionTipo(String resolucionTipo) {
        this.resolucionTipo = resolucionTipo;
    }

    public BigDecimal getMontoReembolsado() {
        return montoReembolsado;
    }

    public void setMontoReembolsado(BigDecimal montoReembolsado) {
        this.montoReembolsado = montoReembolsado;
    }

    public String getJustificacionTexto() {
        return justificacionTexto;
    }

    public void setJustificacionTexto(String justificacionTexto) {
        this.justificacionTexto = justificacionTexto;
    }

    public LocalDateTime getFechaApertura() {
        return fechaApertura;
    }

    public void setFechaApertura(LocalDateTime fechaApertura) {
        this.fechaApertura = fechaApertura;
    }

    public LocalDateTime getFechaCierre() {
        return fechaCierre;
    }

    public void setFechaCierre(LocalDateTime fechaCierre) {
        this.fechaCierre = fechaCierre;
    }
    
    
    
    
}
