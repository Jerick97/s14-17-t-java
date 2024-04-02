package com.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class fieldsModel {
    
    private Long id;
    private String name;
    private String description;
    private String status;
    private Integer min;
    private Integer max;
}
