package com.bezy.inventorysystem.dtos;

import com.bezy.inventorysystem.entities.Category;
import lombok.Data;

@Data
public class UpdateProductRequest {
    private Category category;
    private String name;
    private String description;
    private Double price;
    private Integer quantityInStock;
}
