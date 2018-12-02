package com.proyectoPaquetes.command.SignUp;


import com.proyectoPaquetes.command.ValidationRules;
import lombok.Data;
import lombok.ToString;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Date;

@ToString
@Data
public class DireccionSignUpCommand implements Serializable {

        //ÉSTOS SON LOS COMANDOS QUE RIGEN LAS VALIDACIONES A LA HORA DE REGISTRAR UN NUEVO USUARIO
        @NotNull(message = "Por favor, introduzca su direccion.")
        @NotEmpty(message = "Por favor, introduzca su direccion.")
        @Pattern(regexp = ValidationRules.FIRST_LAST_NAME_REGEX, message = "La direccion posee caracteres no válidos.")
        @Size(max = ValidationRules.FIRST_LAST_NAME_MAX_SIZE, message = "La direccion no puede contener más de 40 caracteres.")
        private String direccion1;

        @NotNull(message = "Por favor, introduzca su direccion.")
        @NotEmpty(message = "Por favor, introduzca su direccion.")
        @Size(max = ValidationRules.FIRST_LAST_NAME_MAX_SIZE, message = "La direccion no puede contener más de 40 caracteres.")
        @Pattern(regexp = ValidationRules.FIRST_LAST_NAME_REGEX, message = "La direccion posee caracteres no válidos.")
        private String direccion2;

          @NotNull(message = "Por favor, repita la codigo postal.")
          @NotEmpty(message = "Por favor, repita la codigo postal.")
          @Size(max = ValidationRules.FIRST_LAST_NAME_MAX_SIZE, message = "El codigo postal no puede contener más de 40 caracteres.")
          @Pattern(regexp = ValidationRules.FIRST_LAST_NAME_REGEX, message = "El codigo postal posee caracteres no válidos.")
          private String codigoPostal;

        @NotNull(message = "Por favor, introduzca una ciudad.")
        @NotEmpty(message = "Por favor, introduzca una ciudad.")
        @Size(max = ValidationRules.FIRST_LAST_NAME_MAX_SIZE, message = "La ciudad no puede contener más de 40 caracteres.")
        @Pattern(regexp = ValidationRules.FIRST_LAST_NAME_REGEX, message = "La ciudad posee caracteres no válidos.")
        private String ciudad;

        @NotNull(message = "Por favor, introduzca un pais.")
        @NotEmpty(message = "Por favor, introduzca un pais.")
        @Size(max = ValidationRules.FIRST_LAST_NAME_MAX_SIZE, message = "El pais no puede contener más de 40 caracteres.")
        @Pattern(regexp = ValidationRules.FIRST_LAST_NAME_REGEX, message = "El pais posee caracteres no válidos.")
        private String pais;


        private Long Latitud;

        private Long Longitud;


    public String getDireccion1() {
        return direccion1;
    }

    public void setDireccion1(String direccion1) {
        this.direccion1 = direccion1;
    }

    public String getDireccion2() {
        return direccion2;
    }

    public void setDireccion2(String direccion2) {
        this.direccion2 = direccion2;
    }

    public String getCodigoPostal() {
        return codigoPostal;
    }

    public void setCodigoPostal(String codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public Long getLatitud() {
        return Latitud;
    }

    public void setLatitud(Long latitud) {
        Latitud = latitud;
    }

    public Long getLongitud() {
        return Longitud;
    }

    public void setLongitud(Long longitud) {
        Longitud = longitud;
    }
}
