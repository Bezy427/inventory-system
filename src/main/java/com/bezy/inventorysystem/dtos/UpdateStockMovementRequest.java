package com.bezy.inventorysystem.dtos;

import com.bezy.inventorysystem.entities.Product;
import com.bezy.inventorysystem.entities.Type;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UpdateStockMovementRequest {
    private String reason;
    private LocalDateTime movementDate = LocalDateTime.now();
    private Type type;
    private Product product;
    private Integer quantity;
}
