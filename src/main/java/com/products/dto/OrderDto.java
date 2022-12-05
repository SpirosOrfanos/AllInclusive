package com.products.dto;

import java.io.Serializable;
import java.util.List;

public class OrderDto implements Serializable{

  private Long user;
  private List<OrderItemDto> orderItemDtos;

  public Long getUser() {
    return user;
  }

  public void setUser(Long user) {
    this.user = user;
  }

  public List<OrderItemDto> getOrderItemDtos() {
    return orderItemDtos;
  }

  public void setOrderItemDtos(List<OrderItemDto> orderItemDtos) {
    this.orderItemDtos = orderItemDtos;
  }

  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder();
    builder.append("OrderDto [orderItemDtos=");
    builder.append(orderItemDtos);
    builder.append(", user = ");
    builder.append(user);
    builder.append("]");
    return builder.toString();
  }
  
}
