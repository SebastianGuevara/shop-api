package com.shopapi.shopapi.controllers.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProductDTO {
    private Integer code;
    private String name;
    private float unitPrice;
    private Integer stock;

}