package com.bezy.inventorysystem.dtos;

import com.bezy.inventorysystem.entities.PurchaseOrder;
import com.bezy.inventorysystem.entities.Supplier;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class UpdatePurchaseRequest {
    private BigDecimal totalCost;
    private Supplier supplier;
    private List<PurchaseOrder> items;
}
