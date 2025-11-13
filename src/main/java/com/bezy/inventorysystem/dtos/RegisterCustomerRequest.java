package com.bezy.inventorysystem.dtos;

import com.bezy.inventorysystem.entities.Product;
import lombok.Data;

import java.util.List;

@Data
public class RegisterCustomerRequest {
    private Long id;
    private String name;
    private String email;
    private String phoneNumber;
    private List<Product> products;
}
