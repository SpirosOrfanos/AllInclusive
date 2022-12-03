package com.products.dto;

import java.io.Serializable;
import java.util.List;

public class ProductResponseDto implements Serializable {
  private List<ProductDto> products;

  public ProductResponseDto() {
  }

  public ProductResponseDto(List<ProductDto> products) {
    this.products = products;
  }

  public List<ProductDto> getProducts() {
    return products;
  }

  public void setProducts(List<ProductDto> products) {
    this.products = products;
  }
  
  

}
