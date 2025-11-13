package com.bezy.inventorysystem.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "stock_movement")
public class StockMovement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime movementDate = LocalDateTime.now();

    private Integer quantity;

    @Enumerated(EnumType.STRING)
    private Type type; // IN or OUT

    private String reason; // e.g. Sale, Purchase, Adjustment

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
}
