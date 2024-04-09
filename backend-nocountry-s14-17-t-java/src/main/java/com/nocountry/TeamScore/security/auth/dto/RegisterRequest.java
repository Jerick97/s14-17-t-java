package com.nocountry.TeamScore.security.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest { // esto es un DTO de registro, considerar hacerlo con menos datos?
    private String name;
    private String surname;
    private String email;
    private String status; // considerar que el status sea un booleano, y ver si no es lo mismo que el enabled de spring-boot
    private Integer operador; // no estoy seguro para que es este atributo
    private String password;
    private String rolEnElProyecto;
}
