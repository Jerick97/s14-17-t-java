package com.domain.groupUser;

import java.util.List;
import com.dto.GroupUserModel;
import com.dto.LoadBulkModel;

public interface GroupUserService {

    GroupUserModel createGroupUser(GroupUserModel groupUserModel);
    List<LoadBulkModel> createLoadBulk(List<LoadBulkModel> groupUserModel);

    GroupUserModel updateGroupUser(Long groupUserId, GroupUserModel groupUserModel);

    GroupUserModel getGroupUserById(Long id);
    List<GroupUserModel> getAllGroupUser();

    void deleteGroupUser(Long groupUserId);
}
