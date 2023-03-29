package com.shopapi.shopapi;

import com.shopapi.shopapi.data.Stock;
import com.shopapi.shopapi.repository.IStockRepository;
import com.shopapi.shopapi.service.StockService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
@ExtendWith(MockitoExtension.class)
public class StockTest {
    @Mock
    private IStockRepository stockRepository;
    @InjectMocks
    private StockService stockService;

    @Test
    public void Given_a_stock_with_negative_quantity_When_invoking_addProduct_Then_throw_exception(){
        Stock stock = new Stock(1,"Bimbo",3000,-1,new Date());
        Assertions.assertThrows(RuntimeException.class,()->stockService.addProduct(stock));
    }
    @Test
    public void Given_a_stock_When_invoking_addProduct_Then_save_product(){
        Stock stock = new Stock(1,"Bimbo",3000,100,new Date());
        Mockito.when(stockRepository.save(stock)).thenReturn(stock);
        Assertions.assertEquals(stock,stockService.addProduct(stock));
    }
    @Test
    public void Given_nothing_When_invoking_getProducts_Then_show_all_products(){
        Stock stock = new Stock(1,"Bimbo",3000,100,new Date());
        Mockito.when(stockRepository.findAll()).thenReturn(Collections.singletonList(stock));
        Assertions.assertNotNull(stockService.getProducts());
    }
}
