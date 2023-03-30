package com.shopapi.shopapi;

import com.shopapi.shopapi.data.Sale;
import com.shopapi.shopapi.repository.ISaleRepository;
import com.shopapi.shopapi.service.SaleService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.Date;

@ExtendWith(MockitoExtension.class)
public class SaleTest {
    @Mock
    private ISaleRepository saleRepository;
    @InjectMocks
    private SaleService saleService;

    @Test
    public void Given_a_sale_When_invoking_createSale_Then_save_sale(){
        Sale sale = new Sale(1,1007207933,100,new Date());
        Mockito.when(saleRepository.save(sale)).thenReturn(sale);
        Assertions.assertEquals(sale, saleService.createSale(sale));
    }

    @Test
    public void Given_a_nothing_When_invoking_getSales_Then_show_all_sales(){
        Sale sale = new Sale(1, 1007207933, 100, new Date());
        Mockito.when(saleRepository.findAll()).thenReturn(Collections.singletonList(sale));
        Assertions.assertNotNull(saleService.getSales());
    }

    @Test
    public void Given_a_document_When_invoking_getSaleByUserDocument_Then_show_all_sales_with_the_same_user_document(){
        Sale sale = new Sale(1, 1007207933, 100, new Date());
        Mockito.when(saleRepository.findSaleByUserDocument(sale.getDocumentClient())).thenReturn(Collections.singletonList(sale));
        Assertions.assertNotNull(saleService.getSaleByUserDocument(sale.getDocumentClient()));
    }

}
