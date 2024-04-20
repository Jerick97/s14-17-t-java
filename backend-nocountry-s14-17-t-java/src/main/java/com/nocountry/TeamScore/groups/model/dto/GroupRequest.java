package com.nocountry.TeamScore.groups.model.dto;


import com.nocountry.TeamScore.security.user.model.dto.UserDTO;
import lombok.Builder;
import lombok.Data;

import java.util.Set;

@Data
@Builder
public class GroupRequest { // este ya no lo uso por ahora
   private Long groupId;
}
