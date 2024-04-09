package com.nocountry.TeamScore.security.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationRequest { // para la autenticación solo me interesan estos datos
    private String email;
    private String password;
}
