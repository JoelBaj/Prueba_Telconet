package com.back_sistema.api.model.entity;


import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Entity
@Table(name="residentes")
public class Residente implements Serializable {
    @Id
    @Column(name = "id_residente")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idusuario;

    @Column(name= "username", length = 50)
    private String username;

    @Column(name = "password", length = 60)
    private String password;

    @Column(name = "nombre", unique = true, length = 20)
    private String nombre;

    @Column(name= "apellido", length = 50)
    private String apellido;

    @Column(name= "identificacion", unique = true, length = 10)
    private String identificacion;

    @Column(name= "placavehiculo", length = 50)
    private String placa;

}
