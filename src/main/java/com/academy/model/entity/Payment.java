package com.academy.model.entity;

import lombok.Data;

@Data
public class Payment {
    private Integer id;
    private Order order;
}
