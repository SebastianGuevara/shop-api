package com.shopapi.shopapi.repository;

import com.shopapi.shopapi.data.SaleProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ISaleProductRepository extends JpaRepository<SaleProduct, Integer> {
}
