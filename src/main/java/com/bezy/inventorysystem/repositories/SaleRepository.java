package com.bezy.inventorysystem.repositories;

import com.bezy.inventorysystem.entities.Sale;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SaleRepository extends JpaRepository<Sale, Long> {
}