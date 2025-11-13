package com.bezy.inventorysystem.mappers;

import com.bezy.inventorysystem.dtos.CategoryDto;
import com.bezy.inventorysystem.dtos.RegisterCategoryRequest;
import com.bezy.inventorysystem.dtos.UpdateCategoryRequest;
import com.bezy.inventorysystem.entities.Category;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    CategoryDto toDto(Category category);
    Category toEntity(RegisterCategoryRequest request);
    void update(UpdateCategoryRequest request, @MappingTarget Category category);
}
