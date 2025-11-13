package com.bezy.inventorysystem.repositories;

import com.bezy.inventorysystem.entities.SaleItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SaleItemRepository extends JpaRepository<SaleItem, Long> {
}