package com.bezy.inventorysystem.services;

import com.bezy.inventorysystem.dtos.RegisterSaleRequest;
import com.bezy.inventorysystem.dtos.UpdateSaleRequest;
import com.bezy.inventorysystem.entities.Product;
import com.bezy.inventorysystem.entities.Sale;
import com.bezy.inventorysystem.entities.SaleItem;
import com.bezy.inventorysystem.mappers.SaleMapper;
import com.bezy.inventorysystem.repositories.ProductRepository;
import com.bezy.inventorysystem.repositories.SaleRepository;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.math.BigDecimal;

@Service
public class SaleService {
    private final ProductRepository productRepository;
    private final SaleItemService saleItemService;
    private final SaleRepository saleRepository;
    private final SaleMapper saleMapper;

    public SaleService(ProductRepository productRepository, SaleItemService saleItemService, SaleRepository saleRepository, SaleMapper saleMapper) {
        this.productRepository = productRepository;
        this.saleItemService = saleItemService;
        this.saleRepository = saleRepository;
        this.saleMapper = saleMapper;
    }

    public ResponseEntity<?> createSale(
            @RequestBody RegisterSaleRequest request
    ){
        Sale sale = new Sale();
        sale.setCustomer(request.getCustomer());
        sale.setSaleDate(request.getSaleDate());
        BigDecimal totalPrice = BigDecimal.ZERO;
        for(SaleItem item : request.getSaleItems()) {
            Product product = productRepository.findById(item.getProduct().getId())
                    .orElseThrow(() -> new RuntimeException("Product not found!"));

            BigDecimal unitPrice = product.getPrice();
            BigDecimal itemTotal = unitPrice.multiply(BigDecimal.valueOf(item.getQuantity()));

            if(product.getQuantityInStock() < item.getQuantity()){
                throw new RuntimeException("Product quantity exceeds stock!");
            }
            product.setQuantityInStock(product.getQuantityInStock() - item.getQuantity());
            productRepository.save(product);


            sale.getSaleItems().add(item);
            totalPrice = totalPrice.add(itemTotal);

        }
        sale.setId(request.getId());
        sale.setUser(request.getUser());
        sale.setSaleItems(request.getSaleItems());
        saleRepository.save(sale);
        return  ResponseEntity.ok("Sale Item has been created!");
    }

    public ResponseEntity<?> updateSale(
            @PathVariable Long id,
            @Valid @RequestBody UpdateSaleRequest request
    ){
        var sale = saleRepository.findById(id).orElse(null);
        if (sale == null) {
            return ResponseEntity.notFound().build();
        }
        saleMapper.update(request, sale);
        saleRepository.save(sale);
        return   ResponseEntity.ok("Sale has been updated!");
    }
}
