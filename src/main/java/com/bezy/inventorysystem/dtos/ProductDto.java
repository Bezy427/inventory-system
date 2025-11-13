package com.bezy.inventorysystem.dtos;

import com.bezy.inventorysystem.entities.Category;
import com.bezy.inventorysystem.entities.SaleItem;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class ProductDto {
    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
    private Category category;
    private Integer quantityInStock;
    private List<SaleItem> saleItems;
}
