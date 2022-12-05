package com.products.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import com.products.domain.Orders;
import com.products.domain.Status;
import com.products.dto.OrderDto;
import com.products.repository.OrderRepository;
import com.products.service.mapper.OrderMapper;


@Service
public class OrderService {
  private static final Logger logger = LoggerFactory.getLogger(OrderService.class);

  private OrderRepository orderRepository;
  private OrderMapper orderMapper;
  
  public OrderService(OrderRepository orderRepository,
      OrderMapper orderMapper) {
    this.orderRepository = orderRepository;
    this.orderMapper = orderMapper;
  }
  
  public Long save(OrderDto orderDto) {
    logger.info("{}", orderDto);
    Orders order = orderRepository.save(orderMapper.mapOrder(orderDto, Status.COMPLETED));
    return order.getId();
  }

}
