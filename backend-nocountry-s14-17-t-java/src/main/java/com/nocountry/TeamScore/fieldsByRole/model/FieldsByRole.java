package com.nocountry.TeamScore.fieldsByRole.model;

import com.nocountry.TeamScore.projects.model.Project;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Builder
@Table(name = "fields_by_role")
public class FieldsByRole {
    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne
    private Project project_id;
   // @ManyToOne
  //  private Roles role_id;

}
