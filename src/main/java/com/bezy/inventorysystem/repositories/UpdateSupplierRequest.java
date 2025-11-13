package com.bezy.inventorysystem.repositories;

import lombok.Data;

@Data
public class UpdateSupplierRequest {
    private String name;
    private String email;
    private String address;
    private String contactInfo;
}
