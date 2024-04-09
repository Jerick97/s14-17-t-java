package com.nocountry.TeamScore.groups.model.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GroupRequest {
    private String name;
    //private Set<UserDTO> users; ---comente porque es de user----
}
