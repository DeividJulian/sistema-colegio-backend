package com.colegio.dto;
public class AuthResponseDTO {
    private String token; private String refreshToken; private String rol;
    public AuthResponseDTO(String t,String r,String rol){this.token=t;this.refreshToken=r;this.rol=rol;}
    public String getToken(){return token;} public String getRefreshToken(){return refreshToken;} public String getRol(){return rol;}
}
