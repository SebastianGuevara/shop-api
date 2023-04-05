package com.shopapi.shopapi.service;

import com.shopapi.shopapi.data.Sale;
import com.shopapi.shopapi.data.SaleProduct;
import com.shopapi.shopapi.data.Stock;
import com.shopapi.shopapi.repository.ISaleProductRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;


@ExtendWith(MockitoExtension.class)
public class SaleProductTest {
    @Mock
    private ISaleProductRepository saleProductRepository;
    @InjectMocks
    private SaleProductService saleProductService;



    @Test
    public void Given_a_Product_When_invoking_createSaleProduct_Then_save_all_salesProduct(){
        SaleProduct saleProduct = new SaleProduct(1, new Stock(), 100, new Sale());

        Mockito.when(saleProductRepository.save(saleProduct)).thenReturn(saleProduct);
        Assertions.assertNotNull(saleProductService.createSaleProduct(saleProduct));
    }


}
