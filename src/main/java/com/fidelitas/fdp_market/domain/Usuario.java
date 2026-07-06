package com.fidelitas.fdp_market.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "usuario")
public class Usuario {
    
    // !! falta agregar relacion @ManyToOne hacia Rol

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "correo", unique = true, nullable = false)
    private String correo;

    @Column(name = "clave_hash", nullable = false)
    private String claveHash;
    
    @Column(name = "id_rol", nullable = false)
    private Long idRol;
    
    @Column(name = "saldo_billetera")
    private Double saldoBilletera;
    
    @Column(name = "fecha_registro", insertable = false, updatable = false)
    private java.time.LocalDateTime fechaRegistro;

    @Column(name = "id_vendedor_padre")
    private Long idVendedorPadre;

    @Column(name = "comision_subvendedor_pct")
    private Double comisionSubvendedorPct;

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

    public Long getIdRol() {
        return idRol;
    }

    public void setIdRol(Long idRol) {
        this.idRol = idRol;
    }

    public Double getSaldoBilletera() {
        return saldoBilletera;
    }

    public void setSaldoBilletera(Double saldoBilletera) {
        this.saldoBilletera = saldoBilletera;
    }

    public LocalDateTime getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(LocalDateTime fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public Long getIdVendedorPadre() {
        return idVendedorPadre;
    }

    public void setIdVendedorPadre(Long idVendedorPadre) {
        this.idVendedorPadre = idVendedorPadre;
    }

    public Double getComisionSubvendedorPct() {
        return comisionSubvendedorPct;
    }

    public void setComisionSubvendedorPct(Double comisionSubvendedorPct) {
        this.comisionSubvendedorPct = comisionSubvendedorPct;
    }
    
    
}