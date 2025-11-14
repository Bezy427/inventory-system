package com.bezy.inventorysystem.services;

import com.bezy.inventorysystem.dtos.RegisterSaleItemRequest;
import com.bezy.inventorysystem.dtos.UpdateSaleItemRequest;
import com.bezy.inventorysystem.entities.SaleItem;
import com.bezy.inventorysystem.mappers.SaleItemMapper;
import com.bezy.inventorysystem.repositories.SaleItemRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class SaleItemService {
    private SaleItemRepository saleItemRepository;
    private SaleItemMapper saleItemMapper;

    public ResponseEntity<?> createSaleItem(
            @RequestBody RegisterSaleItemRequest request
    ){
        SaleItem saleItem = new SaleItem();
        saleItem.setId(request.getId());
        saleItem.setQuantity(request.getQuantity());
        saleItem.setUnitPrice(request.getUnitPrice());
        saleItem.setTotalPrice(request.getTotalPrice());
        saleItem.setProduct(request.getProduct());
        saleItem.setSale(request.getSale());
        saleItemRepository.save(saleItem);
        return ResponseEntity.ok("Sale Item has been created!");
    }

    public ResponseEntity<?> updateSaleItem(
            @PathVariable Long id,
            @RequestBody UpdateSaleItemRequest request
    ){
        var saleItem = saleItemRepository.findById(id).orElse(null);
        if (saleItem == null) {
            return ResponseEntity.notFound().build();
        }
        saleItemMapper.update(request, saleItem);
        saleItemRepository.save(saleItem);
        return  ResponseEntity.ok("Sale Item has been updated!");
    }
}
