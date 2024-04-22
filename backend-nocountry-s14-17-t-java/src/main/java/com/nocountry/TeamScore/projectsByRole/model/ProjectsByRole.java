package com.nocountry.TeamScore.projectsByRole.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.nocountry.TeamScore.projects.model.Project;
import com.nocountry.TeamScore.roles.model.Roles;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "projects_by_role")
public class ProjectsByRole {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "project_id")
    private Project project;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "role_id")
    private Roles role;

}
