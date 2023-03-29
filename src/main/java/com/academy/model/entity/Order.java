package com.academy.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Order {

    private Integer id;
    private PlaneRoute planeRoute;
    private Passenger passenger;
    private String departureDataTime;
    private String arrivalDataTime;
    private String status;

}
