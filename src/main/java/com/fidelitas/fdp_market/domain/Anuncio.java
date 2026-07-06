package com.fidelitas.fdp_market.domain;

import java.io.Serializable;
import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "anuncio")
public class Anuncio implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario usuario;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_categoria", nullable = false)
    private Categoria categoria;

    @Column(nullable = false, length = 150)
    private String titulo;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String descripcion;

    @Column(name = "atributo_personalizado", length = 100)
    private String atributoPersonalizado;

    @Column(name = "precio_actual", nullable = false)
    private BigDecimal precioActual;

    @Column(name = "stock_generico")
    private Integer stockGenerico;

    @Column(name = "tipo_entrega", nullable = false, length = 20)
    private String tipoEntrega;

    @Column(nullable = false)
    private boolean activo = true;

    public Anuncio() {
        
    }

    public Anuncio(Long id, Usuario usuario, Categoria categoria, String titulo, String descripcion, String atributoPersonalizado, BigDecimal precioActual, Integer stockGenerico, String tipoEntrega) {
        this.id = id;
        this.usuario = usuario;
        this.categoria = categoria;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.atributoPersonalizado = atributoPersonalizado;
        this.precioActual = precioActual;
        this.stockGenerico = stockGenerico;
        this.tipoEntrega = tipoEntrega;
    }
    
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getAtributoPersonalizado() {
        return atributoPersonalizado;
    }

    public void setAtributoPersonalizado(String atributoPersonalizado) {
        this.atributoPersonalizado = atributoPersonalizado;
    }

    public BigDecimal getPrecioActual() {
        return precioActual;
    }

    public void setPrecioActual(BigDecimal precioActual) {
        this.precioActual = precioActual;
    }

    public Integer getStockGenerico() {
        return stockGenerico;
    }

    public void setStockGenerico(Integer stockGenerico) {
        this.stockGenerico = stockGenerico;
    }

    public String getTipoEntrega() {
        return tipoEntrega;
    }

    public void setTipoEntrega(String tipoEntrega) {
        this.tipoEntrega = tipoEntrega;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }
    
    

}
