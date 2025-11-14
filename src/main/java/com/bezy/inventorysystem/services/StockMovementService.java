package com.bezy.inventorysystem.services;

import com.bezy.inventorysystem.dtos.RegisterStockMovementRequest;
import com.bezy.inventorysystem.dtos.UpdateStockMovementRequest;
import com.bezy.inventorysystem.entities.Product;
import com.bezy.inventorysystem.entities.StockMovement;
import com.bezy.inventorysystem.entities.Type;
import com.bezy.inventorysystem.mappers.StockMovementMapper;
import com.bezy.inventorysystem.repositories.ProductRepository;
import com.bezy.inventorysystem.repositories.StockMovementRepository;
import jakarta.transaction.Transactional;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class StockMovementService {
    private final ProductRepository productRepository;
    private StockMovementRepository stockMovementRepository;
    private StockMovementMapper stockMovementMapper;

    public StockMovementService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public ResponseEntity<?> createStockMovement(
            @RequestBody RegisterStockMovementRequest request
    ){
        StockMovement stockMovement = new StockMovement();
        stockMovement.setId(request.getId());
        stockMovement.setProduct(request.getProduct());
        stockMovement.setMovementDate(request.getMovementDate());
        stockMovement.setType(request.getType());
        stockMovement.setReason(request.getReason());
        stockMovementRepository.save(stockMovement);
        return  ResponseEntity.ok("Stock Movement Created!");
    }

    public ResponseEntity<?> updateStockMovement(
            @PathVariable Long id,
            @RequestBody UpdateStockMovementRequest request
    ){
        var  stockMovement = stockMovementRepository.findById(id).orElse(null);
        if(stockMovement == null){
            return ResponseEntity.notFound().build();
        }
        stockMovementMapper.update(request, stockMovement);
        stockMovementRepository.save(stockMovement);
        return ResponseEntity.ok().build();
    }

    @Transactional
    public void recordStockMovement(Long productId, Integer quantity, Type type, String reason) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found!"));

        if(type == Type.IN){
            product.setQuantityInStock(product.getQuantityInStock() + quantity);
        }else if(type == Type.OUT){
            if(product.getQuantityInStock() < quantity){
                throw new RuntimeException("Insufficient stock!");
            }
            product.setQuantityInStock(product.getQuantityInStock() - quantity);
        }

        productRepository.save(product);
        StockMovement stockMovement = new StockMovement();
        stockMovement.setProduct(product);
        stockMovement.setQuantity(quantity);
        stockMovement.setType(type);
        stockMovement.setReason(reason);

        stockMovementRepository.save(stockMovement);
    }
}
