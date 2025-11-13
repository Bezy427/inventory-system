package com.bezy.inventorysystem.controllers;

import com.bezy.inventorysystem.dtos.RegisterProductRequest;
import com.bezy.inventorysystem.dtos.UpdateProductRequest;
import com.bezy.inventorysystem.mappers.ProductMapper;
import com.bezy.inventorysystem.repositories.ProductRepository;
import com.bezy.inventorysystem.services.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1")
public class ProductController {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final ProductService productService;

    public ProductController(ProductRepository productRepository, ProductMapper productMapper, ProductService productService) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
        this.productService = productService;
    }

    @PostMapping("/products")
    public ResponseEntity<?> createProduct(
            @RequestBody RegisterProductRequest request
    ){
        productService.createProduct(request);
        return ResponseEntity.ok("Product has been created successfully!");
    }

    @PutMapping("/products/{id}")
    public ResponseEntity<?> updateProductById(
            @PathVariable Long id,
            @RequestBody UpdateProductRequest request
    ){
        productService.updateProduct(id, request);
        return ResponseEntity.ok("Product has been updated successfully!");
    }

    @GetMapping("/products")
    public Iterable<?> getAllProducts(){
        return productRepository.findAll()
                .stream()
                .map(productMapper::toDto)
                .toList();
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<?> getProductById(
            @PathVariable Long id
    ){
        var product = productRepository.findById(id).orElse(null);
        if(product == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(productMapper.toDto(product));
    }

    @DeleteMapping("/products/{id} ")
    public ResponseEntity<?> deleteProductById(
            @PathVariable Long id
    ){
        var product = productRepository.findById(id).orElse(null);
        if(product == null){
            return ResponseEntity.notFound().build();
        }
        productRepository.delete(product);
        return ResponseEntity.ok("Product has been deleted successfully!");
    }
}
