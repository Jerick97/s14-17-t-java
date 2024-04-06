package com.domain.user;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user")
public class User {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotBlank(message = "campo 'username' de la clase 'user' no puede quedar en blanco")
    @Column(name = "username", length = 100)
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "temp_hash")
    private String temp_hash;

    @Column(name = "status", length = 45)
    private String status;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "operator", length = 2)
    private Integer operator;

}
