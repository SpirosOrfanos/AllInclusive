package com.products.service.mapper;

import java.util.Date;
import java.util.List;
import org.mapstruct.Mapper;
import com.products.domain.OrderItem;
import com.products.domain.Status;
import com.products.domain.Orders;
import com.products.dto.OrderDto;
import com.products.dto.OrderItemDto;

@Mapper(componentModel = "spring")
public interface OrderMapper {

  Orders map(OrderDto order);
  OrderItem map(OrderItemDto order);
  List<OrderItem> mapList (List<OrderItemDto> order);
  
  default Orders mapOrder(OrderDto order, Status status) {
    Orders orders = new Orders();
    orders.setDatetime(new Date());
    orders.setUser(order.getUser());
    orders.setStatus(status);
    orders.setOrderItems(mapList(order.getOrderItemDtos()));
    for (OrderItem oi : orders.getOrderItems()) {
      oi.setOrder(orders);
    }
    return orders;
  }
}
