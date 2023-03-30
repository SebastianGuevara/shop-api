package com.shopapi.shopapi.service;

import com.shopapi.shopapi.data.Sale;
import com.shopapi.shopapi.repository.ISaleRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

@AllArgsConstructor
@Service
public class SaleService implements ISaleService {
    private final ISaleRepository saleRepository;

    @Override
    public List<Sale> getSaleByUserDocument(Integer document) { return saleRepository.findSaleByUserDocument(document); }

    @Override
    public void preventThreeSalesSameDay(Integer document, Date date) {
        List<Sale> clientSales = saleRepository.getClientCurrentSales(document, date);
        if(3 <= clientSales.size())
            throw new RuntimeException("CAN'T COMPLETE SALE BECAUSE THE CLIENT ALREADY HAS 3 SALES TODAY");
    }

    @Override
    public Sale createSale(Sale sale) {
        return saleRepository.save(sale);
    }

    @Override
    public List<Sale> getSales() {
        return saleRepository.findAll();
    }
}
