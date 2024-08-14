package com.back_sistema.api.model.Dto;

import lombok.Data;

@Data
public class CreateResidenteDto {

    private String username;
    private String password;
    private String nombre;
    private String apellido;
    private String identificacion;
    private String placa;
    //private String role;

}
