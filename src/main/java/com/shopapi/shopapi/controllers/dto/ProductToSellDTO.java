package com.shopapi.shopapi.controllers.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProductToSellDTO {
    private Integer code;
    private Integer unitsToSell;

}
