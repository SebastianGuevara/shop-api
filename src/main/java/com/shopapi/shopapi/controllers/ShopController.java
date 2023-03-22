package com.shopapi.shopapi.controllers;

import com.shopapi.shopapi.controllers.dto.ProductDTO;
import com.shopapi.shopapi.controllers.dto.ProductToSellDTO;
import com.shopapi.shopapi.controllers.dto.StockDTO;
import com.shopapi.shopapi.controllers.dto.StockToAddDTO;
import com.shopapi.shopapi.data.Product;
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
        Stock product = new Stock(stockDTO.getID(),stockDTO.getName(),stockDTO.getValue(),stockDTO.getQuantity(),new Date());
        return new ResponseEntity(shopService.addProduct(product), HttpStatus.CREATED);
    }
    @Operation(summary = "Get all the products.")
    @GetMapping("/products")
    public ResponseEntity getProducts() {
        return new ResponseEntity(shopService.getProducts(), HttpStatus.ACCEPTED);
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
    public ResponseEntity sellProducts(@RequestBody List<ProductToSellDTO> productsToSellDTO) {
        try {
            List<Stock> productsToSell = new ArrayList<>();
            for (ProductToSellDTO productToSellDTO : productsToSellDTO) {
                productsToSell.add(new Stock(productToSellDTO.getCode(),productToSellDTO.getUnitsToSell()));
            }
            return new ResponseEntity(shopService.sellProducts(productsToSell), HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.I_AM_A_TEAPOT);
        }

    }
}
