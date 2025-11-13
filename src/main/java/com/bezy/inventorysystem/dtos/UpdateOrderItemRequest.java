package com.bezy.inventorysystem.dtos;

import com.bezy.inventorysystem.entities.Product;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class UpdateOrderItemRequest {
    private BigDecimal price;
    private Integer quantity;
    private Product product;
}
