package com.bezy.inventorysystem.dtos;

import com.bezy.inventorysystem.entities.Category;
import com.bezy.inventorysystem.entities.SaleItem;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class RegisterProductRequest {
    private Long id;
    private String name;
    private String description;
    private Integer quantityInStock;
    private Category category;
    private List<SaleItem> saleItems;
    private BigDecimal price;
}
