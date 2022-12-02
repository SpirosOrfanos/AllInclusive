package com.products.dto;

import java.io.Serializable;

public class OrderItemDto implements Serializable {

  private Long productId;
  private String productDescription;
  private Long quantity;
  public Long getProductId() {
    return productId;
  }
  public void setProductId(Long productId) {
    this.productId = productId;
  }
  public String getProductDescription() {
    return productDescription;
  }
  public void setProductDescription(String productDescription) {
    this.productDescription = productDescription;
  }
  public Long getQuantity() {
    return quantity;
  }
  public void setQuantity(Long quantity) {
    this.quantity = quantity;
  }
  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder();
    builder.append("OrderItemDto [productId=");
    builder.append(productId);
    builder.append(", productDescription=");
    builder.append(productDescription);
    builder.append(", quantity=");
    builder.append(quantity);
    builder.append("]");
    return builder.toString();
  }
  
}
