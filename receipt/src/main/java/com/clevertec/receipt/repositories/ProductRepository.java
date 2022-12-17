package com.clevertec.receipt.repositories;

import com.clevertec.receipt.models.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,Long> {
}
