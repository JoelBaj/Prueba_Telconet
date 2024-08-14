package com.back_sistema.api.model.Dto;


import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class ResidenteDTO {

    private Integer idusuario;
    private String username;
    private String password;
    private String nombre;
    private String apellido;
    private String identificacion;
    private String placa;
    //private List<Rol> roles;

    public ResidenteDTO(){
    }


}
