package com.nocountry.TeamScore.util;

import lombok.Data;

import java.util.List;

@Data
public class Importation {
    private Long idDelProyecto;
    private String nameGroup;
    private List<UserImportation> usuarios;
}
