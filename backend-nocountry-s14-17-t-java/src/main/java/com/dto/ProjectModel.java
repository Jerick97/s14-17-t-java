package com.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProjectModel {
    
    private Long id;

    private String name;
    private String description;
    private Date publish_date;
    private Date starts_on;
    private Date ends_on;
}
