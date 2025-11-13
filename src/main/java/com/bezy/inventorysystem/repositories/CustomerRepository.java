package com.bezy.inventorysystem.repositories;

import com.bezy.inventorysystem.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}