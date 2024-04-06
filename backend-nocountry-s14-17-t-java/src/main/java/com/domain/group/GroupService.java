package com.domain.group;

import java.util.List;
import com.dto.GroupModel;

public interface GroupService {

    GroupModel createGroup(GroupModel groupModel);
    GroupModel updateGroup(Long groupId, GroupModel groupModel);

    GroupModel getGroupById(Long id);
    List<GroupModel> getAllGroup();

    void deleteGroup(Long groupId);
}
