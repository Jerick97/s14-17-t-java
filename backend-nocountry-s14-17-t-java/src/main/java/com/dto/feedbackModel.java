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
public class feedbackModel {
    
    private Long id;
    private String name;
    private Integer value;
    private String comment;
    private Date date_add;
}
