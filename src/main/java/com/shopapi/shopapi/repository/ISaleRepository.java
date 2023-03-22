package com.shopapi.shopapi.repository;

import com.shopapi.shopapi.data.Sale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ISaleRepository extends JpaRepository<Sale, Integer> {
}
