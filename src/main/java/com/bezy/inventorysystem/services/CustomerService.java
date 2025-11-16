package com.bezy.inventorysystem.services;

import com.bezy.inventorysystem.dtos.RegisterCustomerRequest;
import com.bezy.inventorysystem.dtos.UpdateCustomerRequest;
import com.bezy.inventorysystem.entities.Customer;
import com.bezy.inventorysystem.mappers.CustomerMapper;
import com.bezy.inventorysystem.repositories.CustomerRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    public CustomerService(CustomerRepository customerRepository, CustomerMapper customerMapper) {
        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
    }

    public ResponseEntity<?> registerCustomer(
            @RequestBody RegisterCustomerRequest request
    ){
        Customer customer = new Customer();
        customer.setId(request.getId());
        customer.setName(request.getName());
        customer.setEmail(request.getEmail());
        customer.setPhoneNumber(request.getPhoneNumber());
        customer.setProducts(request.getProducts());
        customerRepository.save(customer);
        return ResponseEntity.ok("Customer has been created successfully!");
    }

    public ResponseEntity<?> updateCustomer(
            @PathVariable Long id,
            @RequestBody UpdateCustomerRequest request
    ){
        var customer = customerRepository.findById(id).orElse(null);
        if(customer == null){
            return ResponseEntity.notFound().build();
        }
        customerMapper.update(request, customer);
        customerRepository.save(customer);
        return ResponseEntity.ok("Customer has been updated successfully!");
    }
}
