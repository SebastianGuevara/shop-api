package com.shopapi.shopapi.repository;

import com.shopapi.shopapi.data.Sale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.validation.Valid;
import java.util.List;
@Repository
public interface ISaleRepository extends JpaRepository<Sale, Integer> {
    @Query(value = "SELECT * FROM SALE WHERE DOCUMENT_CLIENT = :document", nativeQuery = true)
    List<Sale> findSaleByUserDocument(@Param("document") Integer document);
}
