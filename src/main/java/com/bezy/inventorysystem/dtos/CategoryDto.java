package com.bezy.inventorysystem.dtos;

import com.bezy.inventorysystem.entities.Product;
import lombok.Data;

import java.util.List;

@Data
public class CategoryDto {
    private Long id;
    private String name;
    private String description;
    private List<Product> products;
}
