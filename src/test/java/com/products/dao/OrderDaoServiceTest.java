package com.products.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import com.products.domain.Orders;
import com.products.domain.Status;
import com.products.exception.ActionNotSupportedException;
import com.products.exception.NoItemFoundException;
import com.products.repository.OrderRepository;
import com.products.service.dao.OrderDaoService;

@Tag("OrderDaoServiceTest")
@SpringBootTest
public class OrderDaoServiceTest {
  
  private OrderRepository orderRepository;
  private OrderDaoService orderDaoService;
  
  @BeforeEach
  public void before() {
    orderRepository = mock(OrderRepository.class);
    orderDaoService = new OrderDaoService(orderRepository);
    
  }
  
  @Test
  public void getTest() {
    Orders order = new Orders();
    order.setId(1L);
    order.setStatus(Status.COMPLETED);
    //order.setUser(1L);
    
    Orders orders = new Orders();
    orders.setStatus(Status.PENDING);
   // orders.setUser(1L);
    when(orderRepository.findById(1L)).thenReturn(Optional.of(order));
    when(orderRepository.findById(2L)).thenThrow(new  NoItemFoundException("No order found"));
    when(orderRepository.save(orders)).thenReturn(order);
    
    assertEquals(1L, orderDaoService.get(1L).getId());
    assertThrows(NoItemFoundException.class, () -> orderDaoService.get(2L));
    assertEquals(Status.COMPLETED, orderDaoService.save(orders).getStatus());
    
    assertThrows(ActionNotSupportedException.class, () -> orderDaoService.getAll());
    assertThrows(ActionNotSupportedException.class, () -> orderDaoService.getPaginated(PageRequest.of(0, 1)));
    assertThrows(ActionNotSupportedException.class, () -> orderDaoService.getPaginated(PageRequest.of(0, 1)));
    assertThrows(ActionNotSupportedException.class, () -> orderDaoService.delete(1L));
    assertThrows(ActionNotSupportedException.class, () -> orderDaoService.update(order));
    
    
    
  }

}
