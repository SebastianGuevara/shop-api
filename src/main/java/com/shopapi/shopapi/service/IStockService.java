package com.shopapi.shopapi.service;

import com.shopapi.shopapi.data.Stock;

import java.util.List;

public interface IStockService {
    Stock addProduct(Stock stock);

    float getTotalPrice(List<Stock> products);

    String sellProducts(List<Stock> products);

    Stock updateStock(Integer code, Integer stockToaAdd);

    List<Stock> getProducts();
}
