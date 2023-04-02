package com.shopapi.shopapi;

import com.shopapi.shopapi.data.Stock;
import com.shopapi.shopapi.repository.IStockRepository;
import com.shopapi.shopapi.service.StockService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

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
        Stock stock = new Stock(1,"Bimbo",3000,100,new Date());
        Mockito.when(stockRepository.findById(productCode)).thenReturn(Optional.of(stock));
        Assertions.assertThrows(RuntimeException.class,()->stockService.updateStock(productCode,stockToAdd));
    }
    @Test
    public void Given_a_list_of_stocks_When_invoking_getTotalPrice_Then_return_total_price(){
        List<Stock> stocks = new ArrayList<>();
        Stock stock1 = new Stock(1,"Bimbo",3000,1,new Date());
        Stock stock2 = new Stock(2,"Kellogs",3000,1,new Date());
        Stock stock3 = new Stock(3,"Chocorramo",3000,1,new Date());
        stocks.add(stock1);
        stocks.add(stock2);
        stocks.add(stock3);
        for (Stock stock : stocks){
            Mockito.when(stockRepository.findById(stock.getID())).thenReturn(Optional.of(stock));
        }
        Assertions.assertEquals(9000,stockService.getTotalPrice(stocks));
    }
    @Test
    public void Given_stock_to_add_When_invoking_updateStock_Then_add_stock_to_product(){
        Stock stock = new Stock(1,"Bimbo",100,100,new Date());
        Stock newStock = new Stock(1,"Bimbo",130,100,new Date());
        Integer productCode = 1;
        Integer stockToAdd = 30;
        Mockito.when(stockRepository.findById(productCode)).thenReturn(Optional.of(stock));
        Mockito.when(stockRepository.save(stock)).thenReturn(newStock);
        Assertions.assertNotNull(stockService.updateStock(productCode,stockToAdd));
        Assertions.assertEquals(newStock,stockService.updateStock(1,30));
    }
    @Test
    public void Given_a_list_of_products_that_are_more_than_the_stock_When_invoking_sellProducts_Then_throw_exception(){
        Stock stock1 = new Stock(1,"Bimbo",3000,1,new Date());
        List<Stock> stocks = new ArrayList<>();
        Stock newStock1 = new Stock(1,"Bimbo",3000,2,new Date());
        stocks.add(newStock1);
        Mockito.when(stockRepository.findById(stock1.getID())).thenReturn(Optional.of(stock1));
        Assertions.assertThrows(RuntimeException.class, ()->stockService.sellProducts(stocks));
    }
    @Test
    public void Given_a_list_of_products_When_invoking_sellProducts_Then_show_sold_products(){
        Stock stock1 = new Stock(1,"Bimbo",3000,20,new Date());
        Stock stock2 = new Stock(2,"Kellogs",3000,20,new Date());
        List<Stock> stocks = new ArrayList<>();
        Map<String, Integer> soldProducts = new HashMap<>();
        Stock newStock1 = new Stock(1,"Bimbo",3000,2,new Date());
        Stock newStock2 = new Stock(2,"Kellogs",3000,2,new Date());
        soldProducts.put(newStock1.getName(),newStock1.getQuantity());
        soldProducts.put(newStock2.getName(),newStock2.getQuantity());
        stocks.add(newStock1);
        stocks.add(newStock2);
        Mockito.when(stockRepository.findById(stock1.getID())).thenReturn(Optional.of(stock1));
        Mockito.when(stockRepository.findById(stock2.getID())).thenReturn(Optional.of(stock2));
        Assertions.assertEquals(String.format("You sold: %s. And the total price was %f",soldProducts,12000.0),stockService.sellProducts(stocks));
    }
}
