package com.shopapi.shopapi.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="SALE")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Sale
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ID;
    @Column(nullable = false, name = "DOCUMENT_CLIENT", unique = true)
    private Integer documentClient;
    @Column(nullable = false, name = "TOTAL_AMOUNT")
    private Integer totalAmount;
    @Column(nullable = false, name = "DATE_CREATED")
    private Date dateCreated;
}
