package com.nocountry.TeamScore.projects.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProjectDTO {
    private String name;
    private String description;
    private LocalDate publishDate;
    private LocalDateTime startsOn;
    private LocalDateTime endsOn;
}
