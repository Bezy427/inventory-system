package com.bezy.inventorysystem.dtos;

import com.bezy.inventorysystem.entities.Product;
import com.bezy.inventorysystem.entities.Purchase;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class UpdatePurchaseOrderRequest {
    private String status;
    private BigDecimal totalCost;
    private BigDecimal costPerUnit;
    private Integer quantity;
    private Product product;
    private Purchase purchase;
}
