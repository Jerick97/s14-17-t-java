package com.nocountry.TeamScore.security.auth.dto;

import com.nocountry.TeamScore.groups.model.dto.GroupsDTOs;
import com.nocountry.TeamScore.groups.model.dto.GroupsInUsersDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationResponse { // dto de respuesta, solo me interesa el token concepto de jwt

    private String token;
    private String id;
    private String name;
    private String surname;
    private String email;
    private String status;
    private String operador;
    private List<GroupsDTOs> groups;
}
