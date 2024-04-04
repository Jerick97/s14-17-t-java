package com.domain.groupsUser;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import com.dto.GroupsUserModel;
import com.domain.groupsUser.GroupsUser;
import com.domain.groupsUser.GroupsUserRepository;
import com.domain.groupsUser.GroupsUserService;

@Service
public class GroupsUserServiceImpl implements GroupsUserService{

    private final ModelMapper modelMapper;
    private final GroupsUserRepository groupsUserRepository;

    public GroupsUserServiceImpl(
        ModelMapper modelMapper,
        GroupsUserRepository groupsUserRepository
    ) {
        this.modelMapper = modelMapper;
        this.groupsUserRepository = groupsUserRepository;
    }

    @Transactional
    @Override
    public GroupsUserModel createGroupsUser(GroupsUserModel groupsUserModel) {
        
        GroupsUser groupsUser = modelMapper.map(groupsUserModel, GroupsUser.class);
        groupsUser = groupsUserRepository.save(groupsUser);
        groupsUserModel.setId(groupsUser.getId());
        return modelMapper.map(groupsUser, GroupsUserModel.class);
    }

    @Transactional
    @Override
    public GroupsUserModel updateGroupsUser(Long id, GroupsUserModel groupsUserModel) {

        if (groupsUserRepository.existsById(id)) {
            GroupsUser groupsUserActualizado = modelMapper.map(groupsUserModel, GroupsUser.class);
            groupsUserRepository.save(groupsUserActualizado);
            return groupsUserModel;
        }
        throw new ResponseStatusException(
            HttpStatus.NOT_FOUND,
            "GroupsUser no encontrado para actualizar."
        );
    }

    @Override
    public GroupsUserModel getGroupsUserById(Long id) {

        GroupsUser groupsUser = groupsUserRepository.findById(id).orElse(null);
        if (groupsUser == null) {
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND,
                "GroupsUser no encontrado."
            );
        }
        return modelMapper.map(groupsUser, GroupsUserModel.class);
    }

    @Override
    public List<GroupsUserModel> getAllGroupsUser() {

        List<GroupsUser> entity = groupsUserRepository.findAll();
        return entity.stream()
            .map(aux -> modelMapper.map(aux, GroupsUserModel.class))
            .collect(Collectors.toList());
    }

    @Override
    public void deleteGroupsUser(Long id) {
        if (groupsUserRepository.existsById(id)) {
            groupsUserRepository.deleteById(id);
        } else {
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND,
                "GroupsUser no encontrada para eliminar."
            );
        }
    }
}
