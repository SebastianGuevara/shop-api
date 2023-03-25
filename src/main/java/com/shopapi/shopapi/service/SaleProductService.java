package com.shopapi.shopapi.service;

import com.shopapi.shopapi.data.SaleProduct;
import com.shopapi.shopapi.repository.ISaleProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class SaleProductService implements ISaleProductService {
    private final ISaleProductRepository saleProductRepository;

    @Override
    public SaleProduct createSaleProduct(SaleProduct saleProduct) {
        return saleProductRepository.save(saleProduct);
    }
}
