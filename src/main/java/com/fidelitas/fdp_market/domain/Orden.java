package com.fidelitas.fdp_market.domain;

import jakarta.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "orden")
public class Orden implements Serializable {
    
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_cliente", nullable = false)
    private Usuario cliente;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_anuncio", nullable = false)
    private Anuncio anuncio;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_estado_orden", nullable = false)
    private EstadoOrden estadoOrden;

    @Column(name = "id_chat", nullable = false)
    private Long idChat; // eliminar dependencias circulares de Chat

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_subvendedor_gestion")
    private Usuario subvendedorGestion;

    @Column(nullable = false)
    private Integer cantidad;

    @Column(name = "precio_unitario_historico", nullable = false)
    private BigDecimal precioUnitarioHistorico;

    @Column(name = "fee_plataforma", nullable = false)
    private BigDecimal feePlataforma;

    @Column(name = "neto_vendedor_padre", nullable = false)
    private BigDecimal netoVendedorPadre;

    @Column(name = "comision_subvendedor", nullable = false)
    private BigDecimal comisionSubvendedor;

    @Column(name = "rsn_comprador", length = 50)
    private String rsnComprador;

    @Column(name = "fecha_creacion", updatable = false)
    private LocalDateTime fechaCreacion;

    @Column(name = "fecha_liberacion")
    private LocalDateTime fechaLiberacion;

    // hook para asignar hora previo a insert en BD, evidanto boilerplate
    @PrePersist
    protected void onCreate() {
        if (this.fechaCreacion == null) {
            this.fechaCreacion = LocalDateTime.now();
        }
    }

    public Orden() {
    }

    public Orden(Long id, Usuario cliente, Anuncio anuncio, EstadoOrden estadoOrden, Long idChat, Usuario subvendedorGestion, Integer cantidad, BigDecimal precioUnitarioHistorico, BigDecimal feePlataforma, BigDecimal netoVendedorPadre, BigDecimal comisionSubvendedor, String rsnComprador, LocalDateTime fechaCreacion, LocalDateTime fechaLiberacion) {
        this.id = id;
        this.cliente = cliente;
        this.anuncio = anuncio;
        this.estadoOrden = estadoOrden;
        this.idChat = idChat;
        this.subvendedorGestion = subvendedorGestion;
        this.cantidad = cantidad;
        this.precioUnitarioHistorico = precioUnitarioHistorico;
        this.feePlataforma = feePlataforma;
        this.netoVendedorPadre = netoVendedorPadre;
        this.comisionSubvendedor = comisionSubvendedor;
        this.rsnComprador = rsnComprador;
        this.fechaCreacion = fechaCreacion;
        this.fechaLiberacion = fechaLiberacion;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Usuario getCliente() {
        return cliente;
    }

    public void setCliente(Usuario cliente) {
        this.cliente = cliente;
    }

    public Anuncio getAnuncio() {
        return anuncio;
    }

    public void setAnuncio(Anuncio anuncio) {
        this.anuncio = anuncio;
    }

    public EstadoOrden getEstadoOrden() {
        return estadoOrden;
    }

    public void setEstadoOrden(EstadoOrden estadoOrden) {
        this.estadoOrden = estadoOrden;
    }

    public Long getIdChat() {
        return idChat;
    }

    public void setIdChat(Long idChat) {
        this.idChat = idChat;
    }

    public Usuario getSubvendedorGestion() {
        return subvendedorGestion;
    }

    public void setSubvendedorGestion(Usuario subvendedorGestion) {
        this.subvendedorGestion = subvendedorGestion;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public BigDecimal getPrecioUnitarioHistorico() {
        return precioUnitarioHistorico;
    }

    public void setPrecioUnitarioHistorico(BigDecimal precioUnitarioHistorico) {
        this.precioUnitarioHistorico = precioUnitarioHistorico;
    }

    public BigDecimal getFeePlataforma() {
        return feePlataforma;
    }

    public void setFeePlataforma(BigDecimal feePlataforma) {
        this.feePlataforma = feePlataforma;
    }

    public BigDecimal getNetoVendedorPadre() {
        return netoVendedorPadre;
    }

    public void setNetoVendedorPadre(BigDecimal netoVendedorPadre) {
        this.netoVendedorPadre = netoVendedorPadre;
    }

    public BigDecimal getComisionSubvendedor() {
        return comisionSubvendedor;
    }

    public void setComisionSubvendedor(BigDecimal comisionSubvendedor) {
        this.comisionSubvendedor = comisionSubvendedor;
    }

    public String getRsnComprador() {
        return rsnComprador;
    }

    public void setRsnComprador(String rsnComprador) {
        this.rsnComprador = rsnComprador;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public LocalDateTime getFechaLiberacion() {
        return fechaLiberacion;
    }

    public void setFechaLiberacion(LocalDateTime fechaLiberacion) {
        this.fechaLiberacion = fechaLiberacion;
    }
    
    
    
}
