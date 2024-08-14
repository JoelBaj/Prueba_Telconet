package com.back_sistema.api.model.Dto;

import lombok.Data;

import java.util.List;

@Data
public class AuthResponseDTO {
    private String accessToken;

    public AuthResponseDTO(String accessToken) {
        this.accessToken = accessToken;

    }
}
