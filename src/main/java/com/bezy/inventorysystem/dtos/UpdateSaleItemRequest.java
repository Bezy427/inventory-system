package com.bezy.inventorysystem.dtos;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class UpdateSaleItemRequest {
    private BigDecimal unitPrice;
    private Integer quantity;
    private BigDecimal totalPrice;
}
