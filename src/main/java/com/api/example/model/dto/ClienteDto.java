package com.api.example.model.dto;

import lombok.*;

import java.io.Serializable;
import java.util.Date;


@Data
@ToString
@Builder
public class ClienteDto implements Serializable {


    private Integer id_cliente;
    private String nombre;
    private String apellido;
    private String correo;
    private Date fech_registro;

}
