package com.bezy.inventorysystem.dtos;

import com.bezy.inventorysystem.entities.Product;
import com.bezy.inventorysystem.entities.PurchaseOrder;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class RegisterOrderItemRequest {
    private Long id;
    private Product product;
    private PurchaseOrder purchaseOrder;
    private Integer quantity;
    private BigDecimal price;
}
