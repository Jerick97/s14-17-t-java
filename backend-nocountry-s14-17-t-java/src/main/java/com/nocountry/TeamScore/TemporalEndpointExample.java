package com.nocountry.TeamScore;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ejemplos")
public class TemporalEndpointExample {

    @GetMapping("/privado")
    public ResponseEntity<String> holaMundo() {
        return ResponseEntity.ok("Hola mundo");
    }

}
