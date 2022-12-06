package com.products.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.products.annotations.OnlyUserAuth;
import com.products.dto.OrderDto;
import com.products.service.OrderService;

@RestController
@RequestMapping(value = "/api")
@Validated
public class OrderApi {

  @Autowired
  private OrderService orderService;


  @OnlyUserAuth
  @PostMapping(value = {"/order"}, path = {"/order"})
  public ResponseEntity<Long> createOrder(@RequestBody OrderDto order) {
    return ResponseEntity.status(HttpStatus.CREATED).body(orderService.save(order));
  }


}
