package com.shopapi.shopapi.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "SALE_PRODUCT")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SaleProduct
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ID;

    @Column(nullable = false,name = "PRODUCT")
    private Integer saleProduct;

    @Column(nullable = false,name="QUANTITY")
    private Integer quantity;

}
