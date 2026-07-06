package com.fidelitas.fdp_market.dto;

public class LoginRequest {
    private String correo;
    private String password;

    // requerido para serializacion por Jackson (jwt token sesion)
    public LoginRequest() {
    }
    

    // getters y setters
    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
        
}
