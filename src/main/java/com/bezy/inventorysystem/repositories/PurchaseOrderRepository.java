package com.bezy.inventorysystem.repositories;

import com.bezy.inventorysystem.entities.PurchaseOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PurchaseOrderRepository extends JpaRepository<PurchaseOrder, Long> {
}