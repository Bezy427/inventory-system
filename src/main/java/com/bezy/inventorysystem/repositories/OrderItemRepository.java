package com.bezy.inventorysystem.repositories;

import com.bezy.inventorysystem.entities.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}