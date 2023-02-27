package com.shopapi.shopapi.data;

import lombok.*;

import javax.persistence.*;

@Entity
@Table
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    @Id
    private Integer code;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private float unitPrice;
    @Column(nullable = false)
    private Integer stock;
}
