package com.bezy.inventorysystem.repositories;

import com.bezy.inventorysystem.entities.StockMovement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StockMovementRepository extends JpaRepository<StockMovement, Long> {
}