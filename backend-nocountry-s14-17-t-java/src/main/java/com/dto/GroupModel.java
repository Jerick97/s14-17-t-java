package com.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GroupModel {
    
    private Long id;
    private String name;
    private String description;
    private Integer status;
    private ProjectModel project;
}