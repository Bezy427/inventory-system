package com.bezy.inventorysystem.controllers;

import com.bezy.inventorysystem.dtos.CustomerDto;
import com.bezy.inventorysystem.dtos.RegisterCustomerRequest;
import com.bezy.inventorysystem.dtos.UpdateCustomerRequest;
import com.bezy.inventorysystem.entities.Customer;
import com.bezy.inventorysystem.mappers.CustomerMapper;
import com.bezy.inventorysystem.repositories.CustomerRepository;
import com.bezy.inventorysystem.services.CustomerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1")
public class CustomerController {
    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;
    private final CustomerService customerService;

    public CustomerController(CustomerRepository customerRepository, CustomerMapper customerMapper, CustomerService customerService) {
        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
        this.customerService = customerService;
    }

    @PostMapping("/customers")
    public ResponseEntity<?> createCustomer(
            @RequestBody RegisterCustomerRequest request
    ){
        customerService.registerCustomer(request);
        return ResponseEntity.ok("Customer has been created successfully!");
    }

    @PutMapping("/customers/{id}")
    public ResponseEntity<?> updateCustomerById(
            @PathVariable Long id,
            @RequestBody UpdateCustomerRequest request
    ){
        customerService.updateCustomer(id, request);
        return ResponseEntity.ok("Customer has been updated successfully!");
    }

    @GetMapping("/customers")
    public Iterable<CustomerDto> getAllCustomers(){
        return customerRepository.findAll()
                .stream()
                .map(customerMapper::toDto)
                .toList();
    }

    @GetMapping("/customers/{id}")
    public ResponseEntity<CustomerDto> getCustomerById(
            @PathVariable Long id
    ){
        var customer = customerRepository.findById(id).orElse(null);
        if(customer == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(customerMapper.toDto(customer));
    }

    @DeleteMapping("/customers/{id}")
    public ResponseEntity<?> deleteCustomerById(
            @PathVariable Long id
    ){
        var customer = customerRepository.findById(id).orElse(null);
        if(customer == null){
            return ResponseEntity.notFound().build();
        }
        customerRepository.delete(customer);
        return ResponseEntity.ok("Customer has been deleted successfully!");
    }
}
