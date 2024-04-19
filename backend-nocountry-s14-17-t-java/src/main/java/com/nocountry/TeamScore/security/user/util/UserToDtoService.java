package com.nocountry.TeamScore.security.user.util;

import com.nocountry.TeamScore.groups.model.GroupByUser;
import com.nocountry.TeamScore.groups.model.dto.GroupsDTOs;
import com.nocountry.TeamScore.security.user.model.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserToDtoService {

    public GroupsDTOs createGroupsDtoFromUser(User user, GroupByUser groupByUser) {
        GroupsDTOs groupsDTOs = new GroupsDTOs();
        groupsDTOs.setGroup_id(groupByUser.getGroup().getId().toString());
        groupsDTOs.setGroup_name(groupByUser.getGroup().getName());
        groupsDTOs.setChoosen_role(groupByUser.getRolElegido());

        groupsDTOs.setProjectId(groupByUser.getGroup().getProjectId().getId().toString());
        // Aquí se obtiene el nombre del rol y el id del rol
        if (groupByUser.getRole_id() != null) {
            groupsDTOs.setGroup_role_type(groupByUser.getRole_id().getName());
            groupsDTOs.setGroup_role(groupByUser.getRole_id().getId().toString());
        }

        return groupsDTOs;
    }

    public List<GroupsDTOs> createGroupsDtosFromUser(User user) {
        List<GroupsDTOs> groupsDTOsList = new ArrayList<>();
        for (GroupByUser groupByUser : user.getGroupByUserSet()) {
            GroupsDTOs groupsDTOs = createGroupsDtoFromUser(user, groupByUser);
            groupsDTOsList.add(groupsDTOs);
        }
        return groupsDTOsList;
    }
}

