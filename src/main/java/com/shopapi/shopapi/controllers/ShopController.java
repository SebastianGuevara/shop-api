package com.shopapi.shopapi.controllers;

import com.shopapi.shopapi.controllers.dto.*;
import com.shopapi.shopapi.data.Product;
import com.shopapi.shopapi.data.Sale;
import com.shopapi.shopapi.data.SaleProduct;
import com.shopapi.shopapi.data.Stock;
import com.shopapi.shopapi.service.IShopService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameters;
import lombok.AllArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class ShopController {
    private final IShopService shopService;

    @Operation(summary = "Add products to the stock")
    @PostMapping("/product")
    public ResponseEntity addProduct(@RequestBody StockDTO stockDTO) {
        try{
            Stock product = new Stock(stockDTO.getID(),stockDTO.getName(),stockDTO.getValue(),stockDTO.getQuantity(),new Date());
            return new ResponseEntity(shopService.addProduct(product), HttpStatus.CREATED);
        }
        catch (Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.I_AM_A_TEAPOT);
        }

    }
    @Operation(summary = "Get all the products.")
    @GetMapping("/products")
    public ResponseEntity getProducts() {
        try{
            return new ResponseEntity(shopService.getProducts(), HttpStatus.ACCEPTED);
        }
        catch (Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.I_AM_A_TEAPOT);
        }

    }
    @Operation(summary = "Add stock to products")
    @PutMapping("/productStock")
    public ResponseEntity updateStock(@RequestBody StockToAddDTO stockDTO) {
        try {
            Integer code = stockDTO.getID();
            Integer stockToAdd = stockDTO.getQuantityToAdd();
            return new ResponseEntity(shopService.updateStock(code, stockToAdd), HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.I_AM_A_TEAPOT);
        }
    }
    @Operation(summary = "Sell products")
    @PutMapping("/sellProducts")
    public ResponseEntity sellProducts(@RequestBody SellDataDTO sellDataDTO) {
        if(shopService.preventThreeSalesSameDay(sellDataDTO.getClientDocument(), new java.sql.Date(new Date().getTime()))){
            try {
                List<Stock> productsToSell = new ArrayList<>();
                for (ProductToSellDTO product : sellDataDTO.getProducts()) {
                    productsToSell.add(new Stock(product.getCode(),product.getUnitsToSell()));
                }
                Sale sale = shopService.createSale(new Sale(sellDataDTO.getClientDocument(),Float.valueOf(shopService.getTotalSalePrice(productsToSell)).intValue(),new Date()));
                for (ProductToSellDTO product : sellDataDTO.getProducts()) {
                    shopService.createSaleProduct(new SaleProduct(new Stock(product.getCode(),product.getUnitsToSell()),product.getUnitsToSell(), sale));
                }
                return new ResponseEntity(shopService.sellProducts(productsToSell), HttpStatus.ACCEPTED);
            } catch (Exception e){
                return new ResponseEntity(e.getMessage(), HttpStatus.I_AM_A_TEAPOT);
            }
        }
        else{
          return new ResponseEntity("CAN'T COMPLETE SALE BECAUSE THE CLIENT ALREADY HAS 3 SALES TODAY", HttpStatus.I_AM_A_TEAPOT);
        }
    }
    @Operation(summary = "Get user sale history by document")
    @GetMapping("/userSaleHistory/{document}")
    public ResponseEntity getSaleByUserDocument(@PathVariable Integer document){
        try{
            return new ResponseEntity(shopService.getSaleByUserDocument(document),HttpStatus.ACCEPTED);
        }
        catch (Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.I_AM_A_TEAPOT);
        }
    }

}
