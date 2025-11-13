package com.bezy.inventorysystem.controllers;

import com.bezy.inventorysystem.dtos.CategoryDto;
import com.bezy.inventorysystem.dtos.RegisterCategoryRequest;
import com.bezy.inventorysystem.dtos.UpdateCategoryRequest;
import com.bezy.inventorysystem.mappers.CategoryMapper;
import com.bezy.inventorysystem.repositories.CategoryRepository;
import com.bezy.inventorysystem.services.CategoryService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class CategoryController {
    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;
    private final CategoryService categoryService;

    public CategoryController(CategoryRepository categoryRepository, CategoryMapper categoryMapper, CategoryService categoryService) {
        this.categoryRepository = categoryRepository;
        this.categoryMapper = categoryMapper;
        this.categoryService = categoryService;
    }

    @PostMapping("/categories")
    public ResponseEntity<?> createCategory(
            @Valid @RequestBody RegisterCategoryRequest request
    ){
        categoryService.registerCategory(request);
        return ResponseEntity.ok("Category has been created successfully!");
    }

    @PutMapping("/categories/{id}")
    public ResponseEntity<?> updateCategoryById(
            @PathVariable Long id,
            @Valid @RequestBody UpdateCategoryRequest request
    ){
        categoryService.updateCategory(id, request);
        return ResponseEntity.ok("Category has been created!");
    }

    @GetMapping("/categories")
    public List<CategoryDto> getAllCategories(){
        return categoryRepository.findAll()
                .stream()
                .map(categoryMapper::toDto)
                .toList();
    }

    @GetMapping("/categories/{id}")
    public ResponseEntity<?> getCategoryById(
            @PathVariable Long id
    ){
        var category = categoryRepository.findById(id).orElse(null);
        if(category == null){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(categoryMapper.toDto(category));
    }

    @DeleteMapping("/categories/{id}")
    public ResponseEntity<CategoryDto> deleteCategoryById(
            @PathVariable Long id
    ){
        var category = categoryRepository.findById(id).orElse(null);
        if(category == null){
            return ResponseEntity.notFound().build();
        }
        categoryRepository.delete(category);
        return ResponseEntity.ok(categoryMapper.toDto(category));
    }
}
