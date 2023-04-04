package com.shopapi.shopapi.service;

import com.shopapi.shopapi.data.Sale;

import java.util.Date;
import java.util.List;

public interface ISaleService {
    List<Sale> getSaleByUserDocument(Integer document);

    void preventThreeSalesSameDay(Integer document, Date date);

    Sale createSale(Sale sale);

    List<Sale> getSales();
}
