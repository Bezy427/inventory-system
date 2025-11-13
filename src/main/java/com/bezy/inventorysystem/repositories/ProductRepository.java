package com.bezy.inventorysystem.repositories;

import com.bezy.inventorysystem.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.history.RevisionRepository;

import java.util.Arrays;

public interface ProductRepository extends JpaRepository<Product, Long> {
}