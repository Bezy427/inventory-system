package com.bezy.inventorysystem.repositories;

import com.bezy.inventorysystem.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ProductRepository extends JpaRepository<Product, Long> {
}