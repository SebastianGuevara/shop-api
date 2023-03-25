package com.shopapi.shopapi.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@Entity
@Table(name = "STOCK")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Stock {
    public Stock(Integer ID, Integer quantity) {
        this.ID = ID;
        this.quantity = quantity;
    }

    @Id
    private Integer ID;

    @Column(nullable = false, name = "NAME")
    private String name;

    @Column(nullable = false, name = "VALUE")
    private Integer value;

    @Column(nullable = false, name = "QUANTITY")
    private Integer quantity;

    @Column(nullable = false, name = "DATE_CREATED")
    private Date dateCreated;
}
