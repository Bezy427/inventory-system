package com.bezy.inventorysystem.dtos;

import com.bezy.inventorysystem.entities.Product;
import com.bezy.inventorysystem.entities.Purchase;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class PurchaseOrderDto {
    private Long id;
    private Product product;
    private Purchase purchase;
    private Integer quantity;
    private BigDecimal totalCost;
    private BigDecimal costPerUnit;
    private String Status;
}
