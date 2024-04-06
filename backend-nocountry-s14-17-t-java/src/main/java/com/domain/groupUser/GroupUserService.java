package com.domain.groupUser;

import java.util.List;
import com.dto.GroupUserModel;

public interface GroupUserService {

    GroupUserModel createGroupUser(GroupUserModel groupUserModel);
    GroupUserModel updateGroupUser(Long groupUserId, GroupUserModel groupUserModel);

    GroupUserModel getGroupUserById(Long id);
    List<GroupUserModel> getAllGroupUser();

    void deleteGroupUser(Long groupUserId);
}
