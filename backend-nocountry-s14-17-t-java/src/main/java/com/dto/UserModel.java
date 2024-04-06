package com.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserModel {
    
    private Long id;

    private String username;
    private String password;
    private String temp_hash;
    private String status;
    private String name;
    private String surname;
    private Integer operator;

    //Aux
    private String role;
}