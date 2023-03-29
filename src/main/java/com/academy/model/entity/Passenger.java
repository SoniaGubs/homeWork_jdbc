package com.academy.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Passenger {

    private Integer id;
    private String name;
    private String surname;
    private String dateOfBirth;
    private String passportNumber;

}
