package com.shopapi.shopapi.controllers.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class StockToAddDTO {
    private Integer ID;
    private Integer quantityToAdd;
}
