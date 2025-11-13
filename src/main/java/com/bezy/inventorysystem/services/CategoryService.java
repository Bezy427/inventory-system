package com.bezy.inventorysystem.services;

import com.bezy.inventorysystem.dtos.PurchaseMapper;
import com.bezy.inventorysystem.dtos.RegisterCategoryRequest;
import com.bezy.inventorysystem.dtos.RegisterCustomerRequest;
import com.bezy.inventorysystem.dtos.UpdateCategoryRequest;
import com.bezy.inventorysystem.entities.Category;
import com.bezy.inventorysystem.mappers.CategoryMapper;
import com.bezy.inventorysystem.repositories.CategoryRepository;
import jakarta.validation.Valid;
import org.antlr.v4.runtime.misc.LogManager;
import org.hibernate.sql.Update;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    public CategoryService(CategoryRepository categoryRepository, CategoryMapper categoryMapper) {
        this.categoryRepository = categoryRepository;
        this.categoryMapper = categoryMapper;
    }


    public ResponseEntity<?> registerCategory(
            @RequestBody RegisterCategoryRequest request
    ){
        Category category = new Category();
        category.setId(request.getId());
        category.setName(request.getName());
        category.setDescription(request.getDescription());
        category.setProducts(request.getProducts());
        categoryRepository.save(categoryMapper.toEntity(request));
        return ResponseEntity.ok(categoryMapper.toEntity(request));
    }

    public ResponseEntity<?> updateCategory(
            @PathVariable Long id,
            @Valid @RequestBody UpdateCategoryRequest request
    ){
        var category = categoryRepository.findById(id).orElse(null);
        if(category == null){
            return ResponseEntity.notFound().build();
        }
        categoryMapper.update(request, category);
        categoryRepository.save(category);
        return  ResponseEntity.ok(categoryMapper.toDto(category));
    }
}
