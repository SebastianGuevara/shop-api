package com.shopapi.shopapi.repository;

import com.shopapi.shopapi.data.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IShopRepository extends JpaRepository<Product, Integer> {
}
