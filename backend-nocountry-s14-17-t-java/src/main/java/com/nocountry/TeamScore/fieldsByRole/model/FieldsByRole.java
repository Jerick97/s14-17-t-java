package com.nocountry.TeamScore.fieldsByRole.model;

import com.nocountry.TeamScore.projects.model.Project;

import com.nocountry.TeamScore.roles.model.Roles;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor

@Table(name = "fields_by_role")
public class FieldsByRole {
    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne
    private Project project_id;
    @ManyToOne
    private Roles role_id;

}
