package com.nocountry.TeamScore.security.user.model.dto;

import com.nocountry.TeamScore.groups.model.GroupByUser;
import com.nocountry.TeamScore.security.user.model.User;
import com.nocountry.TeamScore.security.user.util.ProgressService;
import lombok.Data;

@Data
public class UsersInGroup {
    private String surname;
    private String name;
    private String email;
    private Boolean emailValidado;
    private String rol;
    private String state;
    private Integer progress;
    private String country; // tendria que agregar en el perfil un endpoint para solicitar este campo.

    public static UsersInGroup fromGroupByUser(GroupByUser groupByUser, ProgressService progressService) {
        User user = groupByUser.getUser();
        UsersInGroup usersInGroup = new UsersInGroup();
        usersInGroup.setSurname(user.getSurname());
        usersInGroup.setName(user.getName());
        usersInGroup.setEmail(user.getEmail());

        // aca va la lógica para establecer 'emailValidado' que todavia no tenemos:
        usersInGroup.setEmailValidado(true);

        usersInGroup.setRol(groupByUser.getRolElegido());

        // si el usuario esta habilitado para ser evaluado
        usersInGroup.setState(user.getStatus());



        usersInGroup.setProgress(progressService.calculateProgress(user, groupByUser.getGroup()));

        usersInGroup.setCountry(user.getCountry());

        return usersInGroup;
    }


}
