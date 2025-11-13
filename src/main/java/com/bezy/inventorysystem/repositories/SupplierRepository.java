package com.bezy.inventorysystem.repositories;

import com.bezy.inventorysystem.entities.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SupplierRepository extends JpaRepository<Supplier, Long> {
  Long id(Long id);
}