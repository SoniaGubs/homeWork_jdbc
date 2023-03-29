package com.academy.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class Crew {

    private Integer id;
    private String fullName;
    private String title;
    private Plane plane;

}
