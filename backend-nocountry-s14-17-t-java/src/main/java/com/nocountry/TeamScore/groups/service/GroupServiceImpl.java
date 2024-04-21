package com.nocountry.TeamScore.groups.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nocountry.TeamScore.groups.model.AsignacionUsuarioRequest;
import com.nocountry.TeamScore.groups.model.Group;
import com.nocountry.TeamScore.groups.model.GroupByUser;
import com.nocountry.TeamScore.groups.model.dto.GroupDTO;
import com.nocountry.TeamScore.groups.repository.GroupByUserRepository;
import com.nocountry.TeamScore.groups.repository.GroupRepository;
import com.nocountry.TeamScore.security.user.model.User;
import com.nocountry.TeamScore.security.user.repository.UserRepository;
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
    public Group getGroupById(Long id) {
        return groupRepository.findById(id).orElseThrow(EntityNotFoundException::new);
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
                    .user(userRepository.findById(idUsuario).get())
                    .group(groupRepository.findById(idGrupo).get())
                    .rolElegido(rol)
                    .build();

            groupByUserRepository.save(etiqueta);
            respuesta = true;
        }
        return respuesta;
    }

    @Override
    public boolean assignUsersToGroup(List<AsignacionUsuarioRequest> requests) {
        boolean respuesta = true;
        for (AsignacionUsuarioRequest request : requests) {
            Long idGrupo = request.getIdGrupo();
            Long idUsuario = request.getIdUsuario();
            String rol = request.getRol();

            if (groupRepository.existsById(idGrupo) && userRepository.existsById(idUsuario)) {
                GroupByUser etiqueta = GroupByUser.builder()
                        .user(userRepository.findById(idUsuario).get())
                        .group(groupRepository.findById(idGrupo).get())
                        .rolElegido(rol)
                        .build();

                groupByUserRepository.save(etiqueta);
            } else {
                respuesta = false;
            }
        }
        return respuesta;
    }

    /*@Override
    public boolean assignUsersToGroup(Long idGrupo, List<Long> idsUsuarios, String rol) {
        if (!groupRepository.existsById(idGrupo)) {
            throw new IllegalArgumentException("El grupo con ID " + idGrupo + " no existe.");
        }

        if (idsUsuarios.isEmpty()) {
            throw new IllegalArgumentException("La lista de IDs de usuarios está vacía.");
        }

        Group group = groupRepository.findById(idGrupo)
                .orElseThrow(() -> new IllegalStateException("No se pudo encontrar el grupo con ID " + idGrupo));

        for (Long idUsuario : idsUsuarios) {
            if (!userRepository.existsById(idUsuario)) {
                throw new IllegalArgumentException("El usuario con ID " + idUsuario + " no existe.");
            }

            User user = userRepository.findById(idUsuario)
                    .orElseThrow(() -> new IllegalStateException("No se pudo encontrar el usuario con ID " + idUsuario));

            GroupByUser groupByUser = GroupByUser.builder()
                    .user(user)
                    .group(group)
                    .rolElegido(rol)
                    .build();

            groupByUserRepository.save(groupByUser);
        }

        return true;
    }*/

    public List<Group> getGroupsByUserEmail(String email) { // TODO usar este metodo en un controlador para traer la lista de groups de un usuario en especifico
        List<GroupByUser> groupByUsers = groupByUserRepository.findByUser_Email(email);
        return groupByUsers.stream()
                .map(GroupByUser::getGroup)
                .collect(Collectors.toList());
    }

    @Override
    public List<GroupByUser> findByUser_Email(String email) {
        return groupByUserRepository.findByUser_Email(email);
    }
}
