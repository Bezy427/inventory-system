package com.bezy.inventorysystem.dtos;

import com.bezy.inventorysystem.entities.Customer;
import com.bezy.inventorysystem.entities.SaleItem;
import com.bezy.inventorysystem.entities.User;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class SaleDto {
    private Long id;
    private Customer customer;
    private User user;
    private List<SaleItem> saleItems;
    private BigDecimal totalAmount;
    private LocalDateTime saleDate = LocalDateTime.now();
}
