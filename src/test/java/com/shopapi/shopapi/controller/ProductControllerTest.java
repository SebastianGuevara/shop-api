package com.shopapi.shopapi.controller;

import com.shopapi.shopapi.AbstractTest;
import com.shopapi.shopapi.controllers.dto.ProductToSellDTO;
import com.shopapi.shopapi.controllers.dto.SellDataDTO;
import com.shopapi.shopapi.controllers.dto.StockDTO;
import com.shopapi.shopapi.controllers.dto.StockToAddDTO;
import com.shopapi.shopapi.data.Stock;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Objects;

public class ProductControllerTest extends AbstractTest {
    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void Given_a_product_When_invoking_addProduct_Then_return_added_product() {
        String path = "/api/product";
        StockDTO dto = new StockDTO(1, "Bimbo", 3000, 20);
        ResponseEntity<Stock> stockResponseEntity = restTemplate.postForEntity(path, dto, Stock.class);
        Assertions.assertEquals(dto.getID(), stockResponseEntity.getBody().getID());
        Assertions.assertEquals(dto.getName(), stockResponseEntity.getBody().getName());
        Assertions.assertEquals(dto.getValue(), stockResponseEntity.getBody().getValue());
        Assertions.assertEquals(dto.getQuantity(), stockResponseEntity.getBody().getQuantity());
        Assertions.assertEquals(HttpStatus.CREATED, stockResponseEntity.getStatusCode());
    }

    @Test
    public void Given_a_exception_When_Invoking_addProduct_Then_Catch_Exception() {
        String path = "/api/product";
        StockDTO dto = new StockDTO(1, "Bimbo", 3000, -20);
        ResponseEntity<String> responseEntity = restTemplate.postForEntity(path, dto, String.class);
        Assertions.assertEquals(HttpStatus.I_AM_A_TEAPOT, responseEntity.getStatusCode());
    }

    @Test
    public void Given_a_list_of_products_When_invoking_getProducts_Then_show_list_of_products() {
        String path = "/api/products";
        ResponseEntity<Stock[]> responseEntity = restTemplate.exchange(path, HttpMethod.GET, null, Stock[].class);
        Assertions.assertEquals(HttpStatus.ACCEPTED, responseEntity.getStatusCode());
        Assertions.assertEquals(1, Objects.requireNonNull(responseEntity.getBody()).length);
    }

    @Test
    public void Given_stockToAddDTO_When_invoking_updateStock_Then_return_ok_response() {
        String path = "/api/productStock";
        StockToAddDTO dto = new StockToAddDTO(1, 20);
        ResponseEntity<Stock> stockResponseEntity = restTemplate.exchange(path, HttpMethod.PUT, new HttpEntity<>(dto), Stock.class);
        Assertions.assertEquals(HttpStatus.ACCEPTED, stockResponseEntity.getStatusCode());
        Assertions.assertEquals(1, stockResponseEntity.getBody().getID());
        Assertions.assertEquals("Bimbo", stockResponseEntity.getBody().getName());
        Assertions.assertEquals(3000, stockResponseEntity.getBody().getValue());
        Assertions.assertEquals(40, stockResponseEntity.getBody().getQuantity());
    }

    @Test
    public void Given_an_exception_When_invoking_updateStock_Then_catch_exception() {
        String path = "/api/productStock";
        StockToAddDTO dto = new StockToAddDTO(1, -20);
        ResponseEntity<String> stockResponseEntity = restTemplate.exchange(path, HttpMethod.PUT, new HttpEntity<>(dto), String.class);
        Assertions.assertEquals(HttpStatus.I_AM_A_TEAPOT, stockResponseEntity.getStatusCode());
    }

    @Test
    public void Given_an_exceptio_When_invoking_sellProducts_Then_catch_exception() {
        String path = "/api/sellProducts";
        ArrayList<ProductToSellDTO> productsToSellDTO = new ArrayList<>();
        ProductToSellDTO productToSellDTO = new ProductToSellDTO(1, 100);
        productsToSellDTO.add(productToSellDTO);
        SellDataDTO dto = new SellDataDTO(productsToSellDTO, 1000398809);
        ResponseEntity<String> stockResponseEntity = restTemplate.exchange(path, HttpMethod.PUT, new HttpEntity<>(dto), String.class);
        Assertions.assertEquals(HttpStatus.I_AM_A_TEAPOT, stockResponseEntity.getStatusCode());
    }

    @Test
    public void Given_an_sellDataDTO_When_invoking_sellProducts_Then_return_sold_products() {
        String path = "/api/sellProducts";
        ArrayList<ProductToSellDTO> productsToSellDTO = new ArrayList<>();
        ProductToSellDTO productToSellDTO = new ProductToSellDTO(1, 10);
        productsToSellDTO.add(productToSellDTO);
        SellDataDTO dto = new SellDataDTO(productsToSellDTO, 1000398809);
        ResponseEntity<String> stockResponseEntity = restTemplate.exchange(path, HttpMethod.PUT, new HttpEntity<>(dto), String.class);
        Assertions.assertEquals(HttpStatus.ACCEPTED, stockResponseEntity.getStatusCode());
        Assertions.assertNotNull(stockResponseEntity.getBody());
    }
}
