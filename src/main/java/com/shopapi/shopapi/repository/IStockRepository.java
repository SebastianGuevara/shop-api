package com.shopapi.shopapi.repository;

import com.shopapi.shopapi.data.Stock;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IStockRepository extends JpaRepository<Stock, Integer> {
}
