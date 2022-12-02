package com.products.service.dao;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.products.domain.Orders;
import com.products.domain.Product;
import com.products.domain.ProductDetails;
import com.products.exception.ActionNotSupportedException;
import com.products.exception.NoItemFoundException;
import com.products.repository.ProductDetailsRepository;
import com.products.repository.ProductRepository;

@Transactional(readOnly = true)
@Service("productDaoService")
public class ProductDaoService implements DaoService<Product, Long>{

  private ProductRepository productRepository;
  
  @Override
  public void update(Product product) {
    throw new ActionNotSupportedException("Order update is not supported (yet)");   
  }

  @Override
  public void delete(Long id) {
    throw new ActionNotSupportedException("Order delete is not supported (yet)");   
    
  }

  @Override
  public Product save(Product product) {
    throw new ActionNotSupportedException("Order save is not supported (yet)");  
  }

  @Override
  public List<Product> getAll() {
    return StreamSupport.stream(productRepository.findAll()
        .spliterator(), false).collect(Collectors.toList());
  }

  @Override
  public List<Product> getPaginated(Pageable paging) {
    return StreamSupport.stream(productRepository.findAll()
        .spliterator(), false).collect(Collectors.toList());
  }
  
  @Override
  public Product get(Long key) {
    return productRepository.findById(key)
        .orElseThrow(() -> new NoItemFoundException("No Product found"));
  }

}
