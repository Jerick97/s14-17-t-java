package com.nocountry.TeamScore;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ejemplos")
public class TemporalEndpointExample {

    @SecurityRequirement(name = "bearerAuth")
    @GetMapping("/privado")
    public ResponseEntity<String> holaMundo() {
        return ResponseEntity.ok("Hola mundo");
    }

}
