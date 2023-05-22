package com.shopapi.shopapi.controllers.rabbitmq;
import com.google.gson.Gson;
import com.shopapi.shopapi.controllers.dto.ProductToSellDTO;
import com.shopapi.shopapi.controllers.dto.SellDataDTO;
import com.shopapi.shopapi.data.Sale;
import com.shopapi.shopapi.data.SaleProduct;
import com.shopapi.shopapi.data.Stock;
import com.shopapi.shopapi.service.ISaleProductService;
import com.shopapi.shopapi.service.ISaleService;
import com.shopapi.shopapi.service.IStockService;
import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
@AllArgsConstructor
public class AmazonListener {
    private final ISaleService saleService;
    private final IStockService stockService;
    private final ISaleProductService saleProductService;
    @RabbitListener(queues = {"Bogota"})
    public void handleOtherClientsSales(String saleMessage){
        Gson gson = new Gson();
        SellDataDTO sellDataDTO = gson.fromJson(saleMessage,SellDataDTO.class);
        try{
            saleService.preventThreeSalesSameDay(sellDataDTO.getClientDocument(), new java.sql.Date(new Date().getTime()));
            List<Stock> productsToSell = new ArrayList<>();
            for (ProductToSellDTO product : sellDataDTO.getProducts()) {
                productsToSell.add(new Stock(product.getCode(), product.getUnitsToSell()));
            }
            Sale sale = saleService.createSale(new Sale(sellDataDTO.getClientDocument(), Float.valueOf(stockService.getTotalPrice(productsToSell)).intValue(), new Date(),"MERCADO LIBRE"));
            for (ProductToSellDTO product : sellDataDTO.getProducts()) {
                saleProductService.createSaleProduct(new SaleProduct(new Stock(product.getCode(), product.getUnitsToSell()), product.getUnitsToSell(), sale));
            }
            stockService.sellProducts(productsToSell);
        }
        catch (Exception e){
            System.out.println(e);
        }

    }

}
