package com.nocountry.TeamScore.security.user.model.dto;

import com.nocountry.TeamScore.groups.model.dto.GroupsInUsersDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class UserDTO {

    private Long id;
    private String name;
    private String surname;
    private String email;
    private String status;
    private Integer operador;
    private List<GroupsInUsersDTO> groups;
}

