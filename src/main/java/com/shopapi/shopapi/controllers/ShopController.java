package com.shopapi.shopapi.controllers;

import com.shopapi.shopapi.controllers.dto.ProductDTO;
import com.shopapi.shopapi.controllers.dto.ProductToSellDTO;
import com.shopapi.shopapi.controllers.dto.StockDTO;
import com.shopapi.shopapi.data.Product;
import com.shopapi.shopapi.service.IShopService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameters;
import lombok.AllArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class ShopController {
    private final IShopService shopService;

    @Operation(summary = "Add products to the stock")
    @PostMapping("/product")
    public ResponseEntity addProduct(@RequestBody ProductDTO productDTO) {
        Product newProduct = new Product(productDTO.getCode(), productDTO.getName(), productDTO.getUnitPrice(), productDTO.getStock());
        return new ResponseEntity(shopService.addProduct(newProduct), HttpStatus.CREATED);
    }

    @Operation(summary = "Get all the products.")
    @GetMapping("/products")
    public ResponseEntity getProducts() {
        return new ResponseEntity(shopService.getProducts(), HttpStatus.ACCEPTED);
    }
    @Operation(summary = "Add stock to products")
    @PutMapping("/productStock")
    public ResponseEntity updateStock(@RequestBody StockDTO stockDTO) {
        try {
            Integer code = stockDTO.getCode();
            Integer stockToAdd = stockDTO.getStockToAdd();
            return new ResponseEntity(shopService.updateStock(code, stockToAdd), HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.I_AM_A_TEAPOT);
        }
    }
    @Operation(summary = "Sell products")
    @PutMapping("/sellProducts")
    public ResponseEntity sellProducts(@RequestBody List<ProductToSellDTO> productsToSellDTO) {
        try {
            List<Product> productsToSell = new ArrayList<>();
            for (ProductToSellDTO productToSellDTO : productsToSellDTO) {
                productsToSell.add(new Product(productToSellDTO.getCode(), "", 0, productToSellDTO.getUnitsToSell()));
            }
            return new ResponseEntity(shopService.sellProducts(productsToSell), HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.I_AM_A_TEAPOT);
        }

    }

}
