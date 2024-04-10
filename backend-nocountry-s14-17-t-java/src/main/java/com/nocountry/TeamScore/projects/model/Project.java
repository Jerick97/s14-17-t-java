package com.nocountry.TeamScore.projects.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.nocountry.TeamScore.feedback.model.Feedback;
import com.nocountry.TeamScore.groups.model.Group;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "projects")
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    @Column(name = "publish_date")
    private LocalDate publishDate;

    @Column(name = "starts_on")
    private LocalDateTime startsOn;

    @Column(name = "ends_on")
    private LocalDateTime endsOn;

    @OneToOne(mappedBy = "projectId")
    private Group group;

//    @ManyToOne(mappedBy = "projectId")
//    private FieldsByProject fieldsByProject;

    @OneToMany(mappedBy = "proyectoEvaluado")
    @JsonIgnore
    private Set<Feedback> feedbacksDelProyecto;
}
