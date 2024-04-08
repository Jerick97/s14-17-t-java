package com.nocountry.TeamScore.groups.model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.Set;
@Getter
@Setter
@Entity
@Table(name = "_groups")
public class Group {

    @Id
    @GeneratedValue
    private Long id;
    private Long projectId; // esto es una FK pero de momento la dejo en Long hasta crear su correspondiente entidad
    private String name;
    private String description;
    private Integer status; // el status de un grupo es para saber si esta activo o no
    @OneToMany(mappedBy = "group_id")
    @JsonIgnore // para que el mapeo con un dto ignore esta propiedad
    private Set<GroupByUser> groupByUserSet;
}
