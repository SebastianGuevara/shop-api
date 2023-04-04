package com.shopapi.shopapi.controllers.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class StockDTO {
    private Integer ID;
    private String name;
    private Integer value;
    private Integer quantity;
}
