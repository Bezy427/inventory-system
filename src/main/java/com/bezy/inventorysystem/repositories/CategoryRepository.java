package com.bezy.inventorysystem.repositories;

import com.bezy.inventorysystem.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
