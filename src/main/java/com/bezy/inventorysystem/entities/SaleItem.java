package com.bezy.inventorysystem.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Entity
@Table(name = "sale_items")
public class SaleItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @ManyToOne
    @JoinColumn(name = "sale_id")
    private Sale sale;

    @Column(name = "quantities")
    private Integer quantity;

    @Column(name = "unit_prices")
    private BigDecimal unitPrice;

    @Column(name = "total_prices")
    private BigDecimal totalPrice;

    @PrePersist
    @PreUpdate
    public void calculateTotalPrice(){
        if(unitPrice != null && quantity != null){
            this.totalPrice = unitPrice.multiply(new BigDecimal(quantity));
        }else{
            this.totalPrice = BigDecimal.ZERO;
        }
    }
}
