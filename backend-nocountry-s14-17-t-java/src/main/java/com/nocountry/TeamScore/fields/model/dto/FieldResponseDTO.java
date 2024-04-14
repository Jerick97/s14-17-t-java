package com.nocountry.TeamScore.fields.model.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FieldResponseDTO {
    private Long id;
    private String name;
    private String description;
    private String status;
    private Integer min;
    private Integer max;
    private Integer allowComment;
}
