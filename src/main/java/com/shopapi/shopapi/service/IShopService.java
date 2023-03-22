package com.shopapi.shopapi.service;

import com.shopapi.shopapi.data.Product;
import com.shopapi.shopapi.data.Sale;
import com.shopapi.shopapi.data.SaleProduct;
import com.shopapi.shopapi.data.Stock;

import java.util.List;


public interface IShopService {
    Stock addProduct(Stock stock);
    String sellProducts(List<Stock> products);
    Stock updateStock(Integer code, Integer stockToaAdd);
    List<Stock> getProducts();
    Sale createSale(Sale sale);
    SaleProduct createSaleProduct(SaleProduct saleProduct);
    float getTotalSalePrice(List<Stock> products);
}