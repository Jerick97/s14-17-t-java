package com.domain.groups;

import java.util.List;
import com.dto.GroupsModel;

public interface GroupsService {

    GroupsModel createGroups(GroupsModel groupsModel);
    GroupsModel updateGroups(Long groupsId, GroupsModel groupsModel);

    GroupsModel getGroupsById(Long id);
    List<GroupsModel> getAllGroups();

    void deleteGroups(Long groupsId);
}
