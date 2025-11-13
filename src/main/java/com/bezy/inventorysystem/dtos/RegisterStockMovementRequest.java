package com.bezy.inventorysystem.dtos;

import com.bezy.inventorysystem.entities.Product;
import com.bezy.inventorysystem.entities.Type;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class RegisterStockMovementRequest {
    private Long id;
    private Type type;
    private String reason;
    private Product product;
    private Integer quantity;
    private LocalDateTime movementDate = LocalDateTime.now();
}
