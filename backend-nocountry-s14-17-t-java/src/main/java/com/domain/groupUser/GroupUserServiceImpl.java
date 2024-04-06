package com.domain.groupUser;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import com.domain.group.GroupService;
import com.domain.user.UserService;
import com.dto.GroupModel;
import com.dto.GroupUserModel;
import com.dto.LoadBulkModel;
import com.dto.UserModel;

@Service
public class GroupUserServiceImpl implements GroupUserService{

    private final ModelMapper modelMapper;
    private final GroupUserRepository groupUserRepository;

    private final GroupService groupService;
    private final UserService userService;

    public GroupUserServiceImpl(
        ModelMapper modelMapper,
        GroupUserRepository groupUserRepository,
        GroupService groupService,
        UserService userService
    ) {
        this.modelMapper = modelMapper;
        this.groupUserRepository = groupUserRepository;
        this.groupService = groupService;
        this.userService = userService;
    }

    @Transactional
    @Override
    public GroupUserModel createGroupUser(GroupUserModel groupUserModel) {
        
        GroupUser groupUser = modelMapper.map(groupUserModel, GroupUser.class);
        groupUser = groupUserRepository.save(groupUser);
        groupUserModel.setId(groupUser.getId());
        return modelMapper.map(groupUser, GroupUserModel.class);
    }

    
	@Override
	public List<LoadBulkModel> createLoadBulk(List<LoadBulkModel> groupUserModels) {

        for (LoadBulkModel loadBulkModel : groupUserModels) {
            
            GroupModel group = groupService.getGroupByNameCreate( loadBulkModel.getNameGroup() );

            for (UserModel user : loadBulkModel.getUsers()) {
                
                String role = user.getRole();
                user = userService.getUserByUserNameCreate( user );

                GroupUserModel groupUserModel = new GroupUserModel();
                groupUserModel.setGroup(group);
                groupUserModel.setUser(user);
                groupUserModel.setRole(role);

                createGroupUser(groupUserModel);
            }
        }

        //envio de mensajes
        for (LoadBulkModel loadBulkModel : groupUserModels) {
            for (UserModel user : loadBulkModel.getUsers()) {
                userService.sentMessageEmail( user );
            }
        }

        return groupUserModels;
	}

    @Transactional
    @Override
    public GroupUserModel updateGroupUser(Long id, GroupUserModel groupUserModel) {

        if (groupUserRepository.existsById(id)) {
            GroupUser groupUserActualizado = modelMapper.map(groupUserModel, GroupUser.class);
            groupUserRepository.save(groupUserActualizado);
            return groupUserModel;
        }
        throw new ResponseStatusException(
            HttpStatus.NOT_FOUND,
            "GroupUser no encontrado para actualizar."
        );
    }

    @Override
    public GroupUserModel getGroupUserById(Long id) {

        GroupUser groupUser = groupUserRepository.findById(id).orElse(null);
        if (groupUser == null) {
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND,
                "GroupUser no encontrado."
            );
        }
        return modelMapper.map(groupUser, GroupUserModel.class);
    }

    @Override
    public List<GroupUserModel> getAllGroupUser() {

        List<GroupUser> entity = groupUserRepository.findAll();
        return entity.stream()
            .map(aux -> modelMapper.map(aux, GroupUserModel.class))
            .collect(Collectors.toList());
    }

    @Override
    public void deleteGroupUser(Long id) {
        if (groupUserRepository.existsById(id)) {
            groupUserRepository.deleteById(id);
        } else {
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND,
                "GroupUser no encontrada para eliminar."
            );
        }
    }
}
