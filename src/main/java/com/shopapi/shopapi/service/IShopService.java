package com.shopapi.shopapi.service;

import com.shopapi.shopapi.data.Product;

import java.util.List;


public interface IShopService {
    Product addProduct(Product product);

    String sellProducts(List<Product> products);

    Product updateStock(Integer code, Integer stockToaAdd);

    List<Product> getProducts();
}
