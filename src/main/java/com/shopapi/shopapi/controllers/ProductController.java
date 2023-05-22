package com.shopapi.shopapi.controllers;

import com.shopapi.shopapi.controllers.dto.ProductToSellDTO;
import com.shopapi.shopapi.controllers.dto.SellDataDTO;
import com.shopapi.shopapi.controllers.dto.StockDTO;
import com.shopapi.shopapi.controllers.dto.StockToAddDTO;
import com.shopapi.shopapi.data.Sale;
import com.shopapi.shopapi.data.SaleProduct;
import com.shopapi.shopapi.data.Stock;
import com.shopapi.shopapi.service.ISaleProductService;
import com.shopapi.shopapi.service.ISaleService;
import com.shopapi.shopapi.service.IStockService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class ProductController {
    private final ISaleService saleService;
    private final ISaleProductService saleProductService;
    private final IStockService stockService;

    @CrossOrigin(origins = "*")
    @Operation(summary = "Add products to the stock")
    @PostMapping("/product")
    public ResponseEntity addProduct(@RequestBody StockDTO stockDTO) {
        try {
            Stock product = new Stock(stockDTO.getID(), stockDTO.getName(), stockDTO.getValue(), stockDTO.getQuantity(), new Date());
            return new ResponseEntity(stockService.addProduct(product), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.I_AM_A_TEAPOT);
        }

    }

    @CrossOrigin(origins = "*")
    @Operation(summary = "Get all the products.")
    @GetMapping("/products")
    public ResponseEntity getProducts() {
        try {
            return new ResponseEntity(stockService.getProducts(), HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.I_AM_A_TEAPOT);
        }

    }
    @CrossOrigin(origins = "*")
    @Operation(summary = "Add stock to products")
    @PutMapping("/productStock")
    public ResponseEntity updateStock(@RequestBody StockToAddDTO stockDTO) {
        try {
            Integer code = stockDTO.getID();
            Integer stockToAdd = stockDTO.getQuantityToAdd();
            return new ResponseEntity(stockService.updateStock(code, stockToAdd), HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.I_AM_A_TEAPOT);
        }
    }
    @CrossOrigin(origins = "*")
    @Operation(summary = "Sell products")
    @PutMapping("/sellProducts")
    public ResponseEntity sellProducts(@RequestBody SellDataDTO sellDataDTO) {
        try {
            saleService.preventThreeSalesSameDay(sellDataDTO.getClientDocument(), new java.sql.Date(new Date().getTime()));
            List<Stock> productsToSell = new ArrayList<>();
            for (ProductToSellDTO product : sellDataDTO.getProducts()) {
                productsToSell.add(new Stock(product.getCode(), product.getUnitsToSell()));
            }
            Sale sale = saleService.createSale(new Sale(sellDataDTO.getClientDocument(), Float.valueOf(stockService.getTotalPrice(productsToSell)).intValue(), new Date()));
            for (ProductToSellDTO product : sellDataDTO.getProducts()) {
                saleProductService.createSaleProduct(new SaleProduct(new Stock(product.getCode(), product.getUnitsToSell()), product.getUnitsToSell(), sale));
            }
            return new ResponseEntity(stockService.sellProducts(productsToSell), HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.I_AM_A_TEAPOT);
        }
    }
}
