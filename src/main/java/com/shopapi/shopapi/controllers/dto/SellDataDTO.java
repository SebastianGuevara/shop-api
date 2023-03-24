package com.shopapi.shopapi.controllers.dto;

import com.shopapi.shopapi.data.Stock;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
@Data
@AllArgsConstructor
public class SellDataDTO {
    private List<ProductToSellDTO> products;
    private Integer clientDocument;
}
