package com.products.enums;

public enum ProductType {
  SMOOTHIES(1L)
  ;
  private Long id;
  
  ProductType(Long id) {
    this.id = id;
  }

  public Long getId() {
    return id;
  }
  
  

}
