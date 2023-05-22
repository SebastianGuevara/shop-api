package com.shopapi.shopapi.controllers;

import com.shopapi.shopapi.service.ISaleService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class SaleController {
    private final ISaleService saleService;

    @CrossOrigin(origins = "*")
    @Operation(summary = "Get user sale history by document")
    @GetMapping("/userSaleHistory/{document}")
    public ResponseEntity getSaleByUserDocument(@PathVariable Integer document) {
        try {
            return new ResponseEntity(saleService.getSaleByUserDocument(document), HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.I_AM_A_TEAPOT);
        }
    }
    @CrossOrigin(origins = "*")
    @Operation(summary = "Get all the sales.")
    @GetMapping("/sale")
    public ResponseEntity getSales() {
        try {
            return new ResponseEntity(saleService.getSales(), HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.I_AM_A_TEAPOT);
        }

    }
}
