package com.bezy.inventorysystem.dtos;

import com.bezy.inventorysystem.entities.Category;
import com.bezy.inventorysystem.entities.PurchaseOrder;
import com.bezy.inventorysystem.entities.Supplier;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class PurchaseDto {
    private Long id;
    private LocalDateTime purchaseDate = LocalDateTime.now();
    private BigDecimal totalCost;
    private Supplier supplier;
    private List<PurchaseOrder> items;
}

