package com.nocountry.TeamScore.groups.service;

import com.nocountry.TeamScore.groups.model.AsignacionUsuarioRequest;
import com.nocountry.TeamScore.groups.model.Group;
import com.nocountry.TeamScore.groups.model.GroupByUser;
import com.nocountry.TeamScore.groups.model.dto.GroupDTO;
import com.nocountry.TeamScore.util.Importation;

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

    //boolean assignUsersToGroup(Long idGrupo, List<Long> idsUsuarios, String rol);
    boolean assignUsersToGroup(List<AsignacionUsuarioRequest> requests);


    List<Group> getGroupsByUserEmail(String email);

    List<GroupByUser> findByUser_Email(String email);
    Group getOrCreateGroup(String name);

    Importation importData(Importation importation);
}
