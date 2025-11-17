package com.bezy.inventorysystem.services;

import com.bezy.inventorysystem.dtos.RegisterSupplierRequest;
import com.bezy.inventorysystem.entities.Supplier;
import com.bezy.inventorysystem.mappers.SupplierMapper;
import com.bezy.inventorysystem.repositories.SupplierRepository;
import com.bezy.inventorysystem.repositories.UpdateSupplierRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class SupplierService {
    private final SupplierRepository supplierRepository;
    private final SupplierMapper supplierMapper;

    public SupplierService(SupplierRepository supplierRepository, SupplierMapper supplierMapper) {
        this.supplierRepository = supplierRepository;
        this.supplierMapper = supplierMapper;
    }

    public ResponseEntity<?> createSupplier(
            @RequestBody RegisterSupplierRequest request
    ){
        Supplier supplier = new Supplier();
        supplier.setName(request.getName());
        supplier.setContactInfo(request.getContactInfo());
        supplier.setEmail(request.getEmail());
        supplier.setAddress(request.getAddress());
        supplier.setId(request.getId());
        supplier.setCategory(request.getCategory());
        supplier.setProduct(request.getProduct());
        supplierRepository.save(supplierMapper.toEntity(request));
        return ResponseEntity.ok().build();
    }

    public ResponseEntity<?> updateSupplier(
            @PathVariable Long id,
            @RequestBody UpdateSupplierRequest request
    ){
        var supplier = supplierRepository.findById(id).orElse(null);
        if(supplier == null){
            return ResponseEntity.notFound().build();
        }
        supplierMapper.update(request, supplier);
        supplierRepository.save(supplier);
        return ResponseEntity.ok().build();
    }
}
