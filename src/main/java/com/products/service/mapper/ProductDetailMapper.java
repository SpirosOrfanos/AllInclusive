package com.products.service.mapper;

import java.util.Optional;
import org.mapstruct.Mapper;
import com.products.domain.Product;
import com.products.domain.ProductDetails;
import com.products.dto.ProductDetailsDto;
import com.products.dto.ProductDto;
 
@Mapper(componentModel = "spring")
public interface ProductDetailMapper {
  ProductDetails map(ProductDetailsDto in);
  ProductDetailsDto map(ProductDetails in);
  
  ProductDto map(Product in);
  
  default void merge(ProductDetails productDetails, ProductDetailsDto productDetailsDto) {
    Optional.ofNullable(productDetailsDto.getDetaiInfo())
    .ifPresent(v -> productDetails.setDetaiInfo(v));
    Optional.ofNullable(productDetailsDto.getDescription())
    .ifPresent(v -> productDetails.setDescription(v));
    
  }
  

}
