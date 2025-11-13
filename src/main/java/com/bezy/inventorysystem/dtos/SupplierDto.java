package com.bezy.inventorysystem.dtos;

import com.bezy.inventorysystem.entities.Category;
import com.bezy.inventorysystem.entities.Product;
import lombok.Data;

@Data
public class SupplierDto {
    private Long id;
    private String name;
    private String contactInfo;
    private String email;
    private String address;
    private Category category;
    private Product product;
}
