package com.shopapi.shopapi.data;

import com.shopapi.shopapi.controllers.dto.StockDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "SALE_PRODUCT")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SaleProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ID;

    @ManyToOne
    @JoinColumn(name = "PRODUCT", referencedColumnName = "ID")
    private Stock stock;

    @Column(nullable = false, name = "QUANTITY")
    private Integer quantity;

    @ManyToOne
    @JoinColumn(name = "SALE_ID", referencedColumnName = "ID")
    private Sale sale;

    public SaleProduct(Stock stock, Integer quantity, Sale sale) {
        this.stock = stock;
        this.quantity = quantity;
        this.sale = sale;
    }
}
