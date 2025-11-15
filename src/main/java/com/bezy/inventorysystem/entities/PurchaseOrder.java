package com.bezy.inventorysystem.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Entity
@Table(name = "purchase_order")
public class PurchaseOrder {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "purchase_id")
    private Purchase purchase;

    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    @Column(name = "total_cost")
    private BigDecimal totalCost;

    @Column(name = "cost_per_unit")
    private BigDecimal costPerUnit;

    @Column(name = "status", nullable = false)
    private String status;
}
