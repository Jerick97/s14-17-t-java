package com.nocountry.TeamScore.groups.service;

import com.nocountry.TeamScore.groups.model.Group;
import com.nocountry.TeamScore.groups.model.GroupByUser;
import com.nocountry.TeamScore.groups.model.dto.GroupDTO;

import java.util.List;
import java.util.Set;

public interface GroupService {
    void crearGrupo(GroupDTO groupDTO);

    Group getGroupById(Long id);
    GroupDTO getGrupo(Long id);
    void modificarGrupo(GroupDTO groupDTO);
    void eliminarGrupo(Long id);

    Set<GroupDTO> getAllGroups();

    /**
     * este método devuelve true o false si pudo asignar el usuario al grupo
     * */
    boolean asignarUsuarioAlGrupoConId(Long idGrupo, Long idUsuario, String roleDelUsuario);

    List<Group> getGroupsByUserEmail(String email);

    List<GroupByUser> findByUser_Email(String email);
}
