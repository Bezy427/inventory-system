package com.bezy.inventorysystem.repositories;

import com.bezy.inventorysystem.entities.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PurchaseRepository extends JpaRepository<Purchase, Long> {
}