package com.shopapi.shopapi;

import com.shopapi.shopapi.data.Sale;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.jdbc.Sql;

public class SaleControllerTest extends AbstractTest {
    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    @Sql(statements = "INSERT INTO SALE (DOCUMENT_CLIENT,TOTAL_AMOUNT,DATE_CREATED) VALUES (1000698810,3000,'2023-03-23T05:00:00.000+00:00')")
    public void Given_a_document_When_invoking_getSaleByUserDocument_Then_return_client_sales() {
        String path = "/api/userSaleHistory/1000698810";
        ResponseEntity<Sale[]> responseEntity = restTemplate.exchange(path, HttpMethod.GET, null, Sale[].class);
        Assertions.assertEquals(HttpStatus.ACCEPTED, responseEntity.getStatusCode());
        Assertions.assertEquals(1, responseEntity.getBody().length);
    }

    @Test
    @Sql(statements = "INSERT INTO SALE (DOCUMENT_CLIENT,TOTAL_AMOUNT,DATE_CREATED) VALUES (1000698809,3000,'2023-03-23T05:00:00.000+00:00')")
    public void Given_a_list_of_sales_When_invoking_getSales_Then_return_sales() {
        String path = "/api/sale";
        ResponseEntity<Sale[]> responseEntity = restTemplate.exchange(path, HttpMethod.GET, null, Sale[].class);
        Assertions.assertEquals(HttpStatus.ACCEPTED, responseEntity.getStatusCode());
    }

}
