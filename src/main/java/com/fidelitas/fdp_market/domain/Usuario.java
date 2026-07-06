package com.fidelitas.fdp_market.domain;

import jakarta.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "usuario")
public class Usuario implements Serializable {

    private static final long serialVersionUID = 1L;

    // !! falta agregar relacion @ManyToOne hacia Rol
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 150)
    private String correo;

    @Column(name = "clave_hash", nullable = false, length = 255)
    private String claveHash;

    @Column(name = "fecha_registro", updatable = false)
    private LocalDateTime fechaRegistro;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_rol", nullable = false)
    private Rol rol;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_vendedor_padre")
    private Usuario vendedorPadre;

    @Column(name = "comision_subvendedor_pct")
    private BigDecimal comisionSubvendedorPct;

    @Column(name = "saldo_billetera", nullable = false)
    private BigDecimal saldoBilletera = BigDecimal.ZERO;

    // hook para asignar hora previo a insert en BD, evidanto boilerplate
    @PrePersist
    protected void onCreate() {
        if (this.fechaRegistro == null) {
            this.fechaRegistro = LocalDateTime.now();
        }
    }
    
    
    // getters y setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getClaveHash() {
        return claveHash;
    }

    public void setClaveHash(String claveHash) {
        this.claveHash = claveHash;
    }

    public LocalDateTime getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(LocalDateTime fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    public Usuario getVendedorPadre() {
        return vendedorPadre;
    }

    public void setVendedorPadre(Usuario vendedorPadre) {
        this.vendedorPadre = vendedorPadre;
    }

    public BigDecimal getComisionSubvendedorPct() {
        return comisionSubvendedorPct;
    }

    public void setComisionSubvendedorPct(BigDecimal comisionSubvendedorPct) {
        this.comisionSubvendedorPct = comisionSubvendedorPct;
    }

    public BigDecimal getSaldoBilletera() {
        return saldoBilletera;
    }

    public void setSaldoBilletera(BigDecimal saldoBilletera) {
        this.saldoBilletera = saldoBilletera;
    }
    
    

}
