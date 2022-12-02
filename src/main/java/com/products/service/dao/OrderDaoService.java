package com.products.service.dao;

import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.products.domain.Orders;
import com.products.dto.OrderDto;
import com.products.exception.ActionNotSupportedException;
import com.products.exception.NoItemFoundException;
import com.products.repository.OrderRepository;

@Transactional(readOnly = true)
@Service("orderDaoService")
public class OrderDaoService implements DaoService<Orders, Long>{
  private OrderRepository orderRepository;
  
  public OrderDaoService(OrderRepository orderRepository) {
    this.orderRepository = orderRepository;
  }

  @Override
  public void update(Orders t) {
    throw new ActionNotSupportedException("Order update is not supported (yet)");   
  }

  @Override
  public void delete(Long k) {
    throw new ActionNotSupportedException("Order delete is not supported (yet)");   
    
  }

  @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
  @Override
  public Orders save(Orders order) {
    return orderRepository.save(order);
    
  }

  @Override
  public List<Orders> getAll() {
    throw new ActionNotSupportedException("Order retrieve is not supported (yet)");
  }

  @Override
  public List<Orders> getPaginated(Pageable paging) {
    throw new ActionNotSupportedException("Order retrieve is not supported (yet)");
  }

  @Override
  public Orders get(Long key) {
    return orderRepository.findById(key)
        .orElseThrow(() -> new NoItemFoundException("No order found"));
  }
  
  

}
