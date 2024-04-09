package com.nocountry.TeamScore.security.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationResponse { // dto de respuesta, solo me interesa el token concepto de jwt

    private String token;
}
