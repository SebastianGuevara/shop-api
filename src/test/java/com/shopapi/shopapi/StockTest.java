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
    @Test
    public void Given_negative_stock_When_invoking_updateStock_Then_throw_exception(){
        Integer productCode = 1;
        Integer stockToAdd = -23;
        RuntimeException exception = Assertions.assertThrows(RuntimeException.class,()->stockService.updateStock(productCode,stockToAdd));
        Assertions.assertEquals("Cant not add negative stock",exception.getMessage());
    }
    /*@Test
    public void Given_a_list_of_products_that_are_more_than_the_stock_When_invoking_sellProducts_Then_throw_exception(){
        Stock stock = new Stock(1,"Bimbo",3000,1000,new Date());
        Mockito.when(stockRepository.findById(1).get()).thenReturn(stock);
        //Assertions.assertThrows(RuntimeException.class,()->stockService.sellProducts(stock))
    }*/
    /*@Test
    public void Given_stock_to_add_When_invoking_updateStock_Then_add_stock_to_product(){
        Stock correctStock = new Stock(1,"Bimbo",3000,100,new Date());
        Stock stock = stockService.updateStock(1,30);
        Mockito.when(stockRepository.save(correctStock)).thenReturn(correctStock);
        Assertions.assertNotNull(stockService.updateStock(stock.getID(),30));

    }*/

}
