package com.domain.groupUser;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import com.dto.GroupUserModel;
import com.domain.groupUser.GroupUser;
import com.domain.groupUser.GroupUserRepository;
import com.domain.groupUser.GroupUserService;

@Service
public class GroupUserServiceImpl implements GroupUserService{

    private final ModelMapper modelMapper;
    private final GroupUserRepository groupUserRepository;

    public GroupUserServiceImpl(
        ModelMapper modelMapper,
        GroupUserRepository groupUserRepository
    ) {
        this.modelMapper = modelMapper;
        this.groupUserRepository = groupUserRepository;
    }

    @Transactional
    @Override
    public GroupUserModel createGroupUser(GroupUserModel groupUserModel) {
        
        GroupUser groupUser = modelMapper.map(groupUserModel, GroupUser.class);
        groupUser = groupUserRepository.save(groupUser);
        groupUserModel.setId(groupUser.getId());
        return modelMapper.map(groupUser, GroupUserModel.class);
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
