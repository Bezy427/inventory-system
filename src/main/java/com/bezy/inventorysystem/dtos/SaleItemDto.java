package com.bezy.inventorysystem.dtos;

import com.bezy.inventorysystem.entities.Product;
import com.bezy.inventorysystem.entities.Sale;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class SaleItemDto {
    private Long id;
    private Product product;
    private Sale sale;
    private Integer quantity;
    private BigDecimal unitPrice;
    private BigDecimal totalPrice;
}
