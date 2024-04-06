package com.domain.group;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import com.dto.GroupModel;
import com.domain.group.Group;
import com.domain.group.GroupRepository;
import com.domain.group.GroupService;

@Service
public class GroupServiceImpl implements GroupService{

    private final ModelMapper modelMapper;
    private final GroupRepository groupRepository;

    public GroupServiceImpl(
        ModelMapper modelMapper,
        GroupRepository groupRepository
    ) {
        this.modelMapper = modelMapper;
        this.groupRepository = groupRepository;
    }

    @Transactional
    @Override
    public GroupModel createGroup(GroupModel groupModel) {
        
        Group group = modelMapper.map(groupModel, Group.class);
        group = groupRepository.save(group);
        groupModel.setId(group.getId());
        return modelMapper.map(group, GroupModel.class);
    }

    @Transactional
    @Override
    public GroupModel updateGroup(Long id, GroupModel groupModel) {

        if (groupRepository.existsById(id)) {
            Group groupActualizado = modelMapper.map(groupModel, Group.class);
            groupRepository.save(groupActualizado);
            return groupModel;
        }
        throw new ResponseStatusException(
            HttpStatus.NOT_FOUND,
            "Group no encontrado para actualizar."
        );
    }

    @Override
    public GroupModel getGroupById(Long id) {

        Group group = groupRepository.findById(id).orElse(null);
        if (group == null) {
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND,
                "Group no encontrado."
            );
        }
        return modelMapper.map(group, GroupModel.class);
    }

    @Override
    public List<GroupModel> getAllGroup() {

        List<Group> entity = groupRepository.findAll();
        return entity.stream()
            .map(aux -> modelMapper.map(aux, GroupModel.class))
            .collect(Collectors.toList());
    }

    @Override
    public void deleteGroup(Long id) {
        if (groupRepository.existsById(id)) {
            groupRepository.deleteById(id);
        } else {
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND,
                "Group no encontrada para eliminar."
            );
        }
    }
}
