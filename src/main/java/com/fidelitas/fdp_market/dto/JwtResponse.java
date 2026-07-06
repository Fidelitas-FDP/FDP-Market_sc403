package com.fidelitas.fdp_market.dto;

public class JwtResponse {
    private String accessToken;
    private String refreshToken;
    private String correo;

    public JwtResponse() {
    }

    public JwtResponse(String accessToken, String refreshToken, String correo) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.correo = correo;
    }
    
    
    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }
    
    
}
