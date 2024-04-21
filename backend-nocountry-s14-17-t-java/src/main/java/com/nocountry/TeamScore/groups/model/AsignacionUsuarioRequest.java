package com.nocountry.TeamScore.groups.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AsignacionUsuarioRequest {
    private Long idGrupo;
    private Long idUsuario;
    private String rol;
}
