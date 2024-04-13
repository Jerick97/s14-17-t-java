package com.nocountry.TeamScore.security.user.model.dto;

import com.nocountry.TeamScore.groups.model.GroupByUser;
import com.nocountry.TeamScore.security.user.model.User;
import lombok.Data;

@Data
public class UsersInGroup {
    private String surname;
    private String name;
    private String email;
    private Boolean emailValidado;
    private String rol;
    private Boolean state;
    private Integer progress;
    private String country; // tendria que agregar en el perfil un endpoint para solicitar este campo.

    public static UsersInGroup fromGroupByUser(GroupByUser groupByUser) {
        User user = groupByUser.getUser();
        UsersInGroup usersInGroup = new UsersInGroup();
        usersInGroup.setSurname(user.getSurname());
        usersInGroup.setName(user.getName());
        usersInGroup.setEmail(user.getEmail());

        // aca va la lógica para establecer 'emailValidado' que todavia no tenemos:
        usersInGroup.setEmailValidado(true);

        usersInGroup.setRol(groupByUser.getRolElegido());

        // si el usuario esta habilitado en el grupo, no es lo mismo que si esta habilitado en security, aca iria una logica con respecto a la fecha del proyecto creo
        usersInGroup.setState(true);


        // aca va la lógica para establecer 'progress'
        usersInGroup.setProgress(100);

        // aca va la lógica para establecer 'country'
        usersInGroup.setCountry("argentina papá");

        return usersInGroup;
    }


}
