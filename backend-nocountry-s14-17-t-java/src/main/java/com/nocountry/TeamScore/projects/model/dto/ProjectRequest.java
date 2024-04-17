package com.nocountry.TeamScore.projects.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
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
public class ProjectRequest {

    @Schema(description = "Name project", example = "TeamScore")
    private String name;

    @Schema(description = "Description project", example = "This a proposal for No-Country to evaluate team members")
    private String description;

    @Schema(description = "Publish date project", example = "2022-01-01")
    private LocalDate publishDate;

    @Schema(description = "Starts on project", example = "2022-01-01 00:00:00")
    private LocalDateTime startsOn;

    @Schema(description = "Ends on project", example = "2022-01-01 00:00:00")
    private LocalDateTime endsOn;
}
