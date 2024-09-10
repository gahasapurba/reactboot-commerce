package com.gahasa.ecommerce.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class JwtResponse implements Serializable {
    private String token;
    private String type = "Bearer";
    private String username;
    private String email;

    public JwtResponse(String accessToken, String username, String email) {
        this.username = username;
        this.email = email;
        this.token = accessToken;
    }
}
