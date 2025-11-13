package com.bezy.inventorysystem.dtos;

import com.bezy.inventorysystem.entities.Product;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.List;

@Data
public class RegisterCategoryRequest {
    private Long id;

    @NotBlank(message = "Name is required!")
    private String name;

    @NotBlank(message = "Description is required!")
    private String description;

    private List<Product> products;
}
