package com.bezy.inventorysystem.services;

import com.bezy.inventorysystem.dtos.RegisterProductRequest;
import com.bezy.inventorysystem.dtos.UpdateProductRequest;
import com.bezy.inventorysystem.entities.Product;
import com.bezy.inventorysystem.mappers.ProductMapper;
import com.bezy.inventorysystem.repositories.CategoryRepository;
import com.bezy.inventorysystem.repositories.ProductRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final CategoryRepository categoryRepository;

    public ProductService(ProductRepository productRepository, ProductMapper productMapper, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
        this.categoryRepository = categoryRepository;
    }

    @Transactional
    public ResponseEntity<?> createProduct(
            @RequestBody RegisterProductRequest request
    ){
        Product product = new Product();
        product.setName(request.getName());
        product.setPrice(request.getPrice());
        product.setCategory(request.getCategory());
        product.setDescription(request.getDescription());
        product.setQuantityInStock(request.getQuantityInStock());
        product.setId(request.getId());
        product.setSaleItems(request.getSaleItems());
        productRepository.save(product);
        return ResponseEntity.ok("Product has been created successfully!");
    }

    public ResponseEntity<?> updateProduct(
            @PathVariable Long id,
            @Valid @RequestBody UpdateProductRequest request
    ){
        var product = productRepository.findById(id).orElse(null);
        if(product == null){
            return ResponseEntity.notFound().build();
        }
        productMapper.update(request, product);
        productRepository.save(product);
        return ResponseEntity.ok("Product has been updated successfully!");
    }
}
