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
        // TODO falta poner lo de role_tiype y id del rol
        groupsDTOs.setGroup_role_type("Usuario"); // por ahora los pongo a todos usuarios
        groupsDTOs.setGroup_role("1");
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

