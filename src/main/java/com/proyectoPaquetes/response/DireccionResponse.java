package com.proyectoPaquetes.response;


import lombok.Data;
import lombok.ToString;


@Data
@ToString
public class DireccionResponse {


    private String idDireccion;

    private String idOrden;


    private String direccion1;

    private String direccion2;

    private String codigoPostal;

    private String Ciudad;

    private String pais;


    private String longitud;


    private String latitud;
}
