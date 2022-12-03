package com.products.dto;

import java.io.Serializable;
import java.util.List;

public class ProductDetailsResponseDto implements Serializable {
  private List<ProductDetailsDto> productDetailsDtos;

  public ProductDetailsResponseDto() {
  }

  public ProductDetailsResponseDto(List<ProductDetailsDto> productDetailsDtos) {
    this.productDetailsDtos = productDetailsDtos;
  }

  public List<ProductDetailsDto> getProductDetailsDtos() {
    return productDetailsDtos;
  }

  public void setProductDetailsDtos(List<ProductDetailsDto> productDetailsDtos) {
    this.productDetailsDtos = productDetailsDtos;
  }

  
  
  

}
