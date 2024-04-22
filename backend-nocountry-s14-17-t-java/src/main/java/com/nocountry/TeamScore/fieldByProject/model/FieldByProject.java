package com.nocountry.TeamScore.fieldByProject.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.nocountry.TeamScore.fields.model.Field;
import com.nocountry.TeamScore.projects.model.Project;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "fields_by_project")
public class FieldByProject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "project_id")
    private Project project;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "field_id")
    private Field field;

    @Column(name = "orderr")
    private Integer order;

    @Column(name = "role")
    private Integer role;

}
