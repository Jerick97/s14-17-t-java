package com.domain.usuario;
import java.util.Collection;
import java.util.List;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import com.domain.rol.Rol;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "usuario")
public class Usuario {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotBlank(message = "campo 'username' de la clase 'Usuario' no puede quedar en blanco")
    @Column(name = "username", length = 50)
    private String username;

    @NotBlank(message = "campo 'password' de la clase 'Usuario' no puede quedar en blanco")
    @Column(name = "password")
    private String password;

    //a: activo
    //d: desactivo
    //p: proceso
    @NotBlank(message = "campo 'enable' de la clase 'Usuario' no puede quedar en blanco")
    @Column(name = "enable", length = 1)
    private String enable;

    @NotNull(message = "campo 'rol' de la clase 'Usuario' no puede ser nulo")
    @ManyToOne
    @JoinColumn(name = "rol_id")
    private Rol rol;

    @Column(name = "name", length = 100)
    private String name;

    @Column(name = "surname", length = 100)
    private String surname;
}
