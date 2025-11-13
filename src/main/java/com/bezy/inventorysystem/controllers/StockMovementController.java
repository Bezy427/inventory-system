package com.bezy.inventorysystem.controllers;

import com.bezy.inventorysystem.dtos.RegisterStockMovementRequest;
import com.bezy.inventorysystem.dtos.StockMovementDto;
import com.bezy.inventorysystem.dtos.UpdateStockMovementRequest;
import com.bezy.inventorysystem.entities.StockMovement;
import com.bezy.inventorysystem.entities.Type;
import com.bezy.inventorysystem.mappers.StockMovementMapper;
import com.bezy.inventorysystem.repositories.StockMovementRepository;
import com.bezy.inventorysystem.services.StockMovementService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class StockMovementController {
    private final StockMovementRepository stockMovementRepository;
    private final StockMovementMapper stockMovementMapper;
    private final StockMovementService stockMovementService;

    public StockMovementController(StockMovementRepository stockMovementRepository, StockMovementMapper stockMovementMapper, StockMovementService stockMovementService) {
        this.stockMovementRepository = stockMovementRepository;
        this.stockMovementMapper = stockMovementMapper;
        this.stockMovementService = stockMovementService;
    }

    @PostMapping("/stock-movement")
    public ResponseEntity<?> createStockMovement(
            @RequestBody RegisterStockMovementRequest request
    ){
        stockMovementService.createStockMovement(request);
        return ResponseEntity.ok("Stock Movement Created!");
    }

    @PutMapping("/stock-movement/{id}")
    public ResponseEntity<?> updateStockMovementById(
            @PathVariable Long id,
            @RequestBody UpdateStockMovementRequest request
    ){
        stockMovementService.updateStockMovement(id, request);
        return ResponseEntity.ok("Stock Movement Updated!");
    }

    @GetMapping("/stock-movement")
    public Iterable<?> getAllStockMovements(){
        return stockMovementRepository.findAll()
                .stream()
                .map(stockMovementMapper::toDto)
                .toList();
    }

    @GetMapping("/stock-movement/{id}")
    public ResponseEntity<?> getStockMovementById(
            @PathVariable Long id
    ){
        var stockMovement = stockMovementRepository.findById(id).orElse(null);
        if(stockMovement == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(stockMovementMapper.toDto(stockMovement));
    }

    @DeleteMapping("/stock-movement/{id}")
    public ResponseEntity<?> deleteStockMovementById(
            @PathVariable Long id
    ){
        var stockMovement = stockMovementRepository.findById(id).orElse(null);
        if(stockMovement == null){
            return ResponseEntity.notFound().build();
        }
        stockMovementRepository.delete(stockMovement);
        return ResponseEntity.ok("Stock Movement Deleted!");
    }

    @PostMapping("/stock-movement/in/{productId}")
    public String stockIn(@PathVariable Long productId, @RequestParam Integer quantity){
        stockMovementService.recordStockMovement(productId, quantity, Type.IN, "Stock added!");
        return "Stock added successfully!";
    }

    @PostMapping("/stock-movement/out/{productId}")
    public String stockOut(@PathVariable Long productId, @RequestParam Integer quantity){
        stockMovementService.recordStockMovement(productId, quantity, Type.OUT, "Stock added!");
        return "Stock added successfully!";
    }

}
