package com.nocountry.TeamScore.groups.model.dto;

import com.nocountry.TeamScore.groups.model.Group;
import com.nocountry.TeamScore.groups.model.GroupByUser;
import com.nocountry.TeamScore.roles.model.Roles;
import lombok.Data;

@Data
public class GroupsInUsersDTO {
    private Long id;
    private String group_name;
    private Roles group_role;
    private String group_role_type;
    private String choosen_role;
    private Long projectId;

    public static GroupsInUsersDTO fromGroupAndGroupByUser(Group group, GroupByUser groupByUser) {
        GroupsInUsersDTO dto = new GroupsInUsersDTO();
        dto.id = group.getId();
        dto.group_name = group.getName();
        dto.group_role = groupByUser.getRole_id();
        dto.group_role_type = groupByUser.getRolElegido();
        dto.choosen_role = groupByUser.getRolElegido();
        dto.projectId = group.getProjectId().getId();
        return dto;
    }
}
