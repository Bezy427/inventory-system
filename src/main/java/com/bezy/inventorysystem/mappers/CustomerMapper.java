package com.bezy.inventorysystem.mappers;

import com.bezy.inventorysystem.dtos.CustomerDto;
import com.bezy.inventorysystem.dtos.RegisterCustomerRequest;
import com.bezy.inventorysystem.dtos.UpdateCustomerRequest;
import com.bezy.inventorysystem.entities.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface CustomerMapper {
    CustomerDto toDto(Customer customer);
    Customer toEntity(RegisterCustomerRequest request);
    void update(UpdateCustomerRequest request, @MappingTarget Customer customer);
}
