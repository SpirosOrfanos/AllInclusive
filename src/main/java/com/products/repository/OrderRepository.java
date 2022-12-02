package com.products.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.products.domain.Orders;

@Repository
public interface OrderRepository extends CrudRepository<Orders, Long> {

}
