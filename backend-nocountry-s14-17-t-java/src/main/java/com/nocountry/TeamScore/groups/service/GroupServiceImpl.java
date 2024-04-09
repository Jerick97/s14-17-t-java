package com.nocountry.TeamScore.groups.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nocountry.TeamScore.groups.model.Group;
import com.nocountry.TeamScore.groups.model.GroupByUser;
import com.nocountry.TeamScore.groups.model.dto.GroupDTO;
import com.nocountry.TeamScore.groups.repository.GroupByUserRepository;
import com.nocountry.TeamScore.groups.repository.GroupRepository;
import com.nocountry.TeamScore.security.user.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class GroupServiceImpl implements GroupService{
    @Autowired
    ObjectMapper mapper;

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private GroupByUserRepository groupByUserRepository;

    @Autowired
    private UserRepository userRepository;

    private void guardarGrupoUtilidad(GroupDTO groupDTO){
        Group group = mapper.convertValue(groupDTO, Group.class);
        groupRepository.save(group);
    }

    @Override
    public void crearGrupo(GroupDTO groupDTO) {
        guardarGrupoUtilidad(groupDTO);
    }

    @Override
    public GroupDTO getGrupo(Long id) { // analizar despues hacer un metodo para buscar por nombre si el front precisa
        Optional<Group> group = groupRepository.findById(id);
        GroupDTO groupDTO = null;
        if (group.isPresent()){
            groupDTO = mapper.convertValue(group, GroupDTO.class);
        }else {
            throw new EntityNotFoundException("El group con el id: " + id + " no existe");
        }
        return groupDTO;
    }

    @Override
    public void modificarGrupo(GroupDTO groupDTO) {
        guardarGrupoUtilidad(groupDTO); // Spring detecta automaticamente si existe si se proporciona un id
    }

    @Override
    public void eliminarGrupo(Long id) {
        groupRepository.deleteById(id);
    }

    @Override
    public Set<GroupDTO> getAllGroups() {
        List<Group> grupos = groupRepository.findAll();
        return grupos.stream()
                .map(grupo-> mapper.convertValue(grupo, GroupDTO.class))
                .collect(Collectors.toSet());
    }

    /**
     * devuelve boolean si pudo crear la etiequeta para el usuario y grupo correspondiente
     * @param idGrupo
     * @param idUsuario
     * @param rol
     * @return
     */
    @Override
   public boolean asignarUsuarioAlGrupoConId(Long idGrupo, Long idUsuario, String rol) {
        boolean respuesta = false;
        if (groupRepository.existsById(idGrupo) && userRepository.existsById(idUsuario)) {
            GroupByUser etiqueta = GroupByUser.builder()
                    .user_id(userRepository.findById(idUsuario).get())
                    .group_id(groupRepository.findById(idGrupo).get())
                    .role(rol)
                    .build();

            groupByUserRepository.save(etiqueta);
            respuesta = true;
        }
        return respuesta;
    }
}
