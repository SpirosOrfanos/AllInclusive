package com.products.dto;

import java.io.Serializable;
import java.util.List;

public class OrderDto implements Serializable{

  private List<OrderItemDto> orderItemDtos;

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
    builder.append("]");
    return builder.toString();
  }
  
}
