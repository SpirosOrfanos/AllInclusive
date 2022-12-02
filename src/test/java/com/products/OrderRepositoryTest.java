package com.products;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import com.products.domain.OrderItem;
import com.products.domain.Orders;
import com.products.domain.Product;
import com.products.domain.Smoothe;
import com.products.domain.Status;
import com.products.repository.OrderRepository;
import com.products.repository.ProductRepository;

@Tag("OrderRepositoryTest")
@DataJpaTest
public class OrderRepositoryTest {

  @Qualifier("productRepository")
  @Autowired
  ProductRepository productRepository;
  @Qualifier("orderRepository")
  @Autowired
  OrderRepository orderRepository;

  @BeforeEach
  public void beforeEach() {
    Product smoothe1 = new Smoothe();
    smoothe1.setDescription("Vanilla");
    smoothe1.setPrice(new BigDecimal("22.50"));

    Product smoothe2 = new Smoothe();
    smoothe2.setDescription("Choco");
    smoothe2.setPrice(new BigDecimal("11.50"));

    Product smoothe3 = new Smoothe();
    smoothe3.setDescription("Strawberry");
    smoothe3.setPrice(new BigDecimal("3.50"));

    Product smoothe4 = new Smoothe();
    smoothe4.setDescription("Hazelnut");
    smoothe4.setPrice(new BigDecimal("15.50"));
    productRepository.saveAll(List.of(smoothe1, smoothe2, smoothe3, smoothe4));
  }

  @Test
  public void createOrder() {

    Orders order = new Orders();
    order.setDatetime(new Date());
    order.setStatus(Status.PENDING);
    order.setOrderItems(new ArrayList<>());
    List<Product> products = StreamSupport.stream(productRepository.findAll().spliterator(), false)
        .collect(Collectors.toList());

    products.stream().filter(v -> v.getDescription().equals("Hazelnut")).findFirst()
        .ifPresent(product -> {
          order.getOrderItems()
              .add(new OrderItem.Builder().setPrice(product.getPrice()).setQuantity(2L)
                  .setProductId(product.getId()).setProductDescription(product.getDescription())
                  .build());
        });
    
    products.stream().filter(v -> v.getDescription().equals("Strawberry")).findFirst()
    .ifPresent(product -> {
      order.getOrderItems()
          .add(new OrderItem.Builder().setPrice(product.getPrice()).setQuantity(2L)
              .setProductId(product.getId()).setProductDescription(product.getDescription())
              .build());
    });

    Orders persisted = orderRepository.save(order);
    OrderItem oi = persisted.getOrderItems().get(0);
    OrderItem oi2 = persisted.getOrderItems().get(1);
    BigDecimal bd1 = (oi.getPrice().multiply(new BigDecimal(oi.getQuantity())));
    BigDecimal bd2 = (oi2.getPrice().multiply(new BigDecimal(oi2.getQuantity())));
    assertEquals(new BigDecimal("38.00"), bd1.add(bd2));
  }
}
