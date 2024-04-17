package com.nocountry.TeamScore.security.user.model.dto;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class UserUpdateRequest {
    @Size(min = 3, max = 65)
    private String name;

    @Size(min = 3, max = 65)
    private String surname;

    private String status;
    private Integer operador;

    // cambios de email o contraseña:
    private String email;
    private String password;
}
