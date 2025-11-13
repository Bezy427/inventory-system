package com.bezy.inventorysystem.dtos;

import com.bezy.inventorysystem.entities.Product;
import com.bezy.inventorysystem.entities.Purchase;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class RegisterPurchaseOrderRequest {
    private Long id;
    private String status;
    private Integer quantity;
    private BigDecimal totalCost;
    private BigDecimal costPerUnit;
    private Purchase purchase;
    private Product product;
}
