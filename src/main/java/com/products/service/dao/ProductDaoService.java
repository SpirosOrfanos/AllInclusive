package com.products.service.dao;

import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.products.domain.Product;
import com.products.exception.ActionNotSupportedException;
import com.products.exception.NoItemFoundException;
import com.products.repository.ProductRepository;

@Transactional(readOnly = true)
@Service("productDaoService")
public class ProductDaoService implements DaoService<Product, Long>{

  private ProductRepository productRepository;
  
  public ProductDaoService(ProductRepository productRepository) {
    this.productRepository = productRepository;
  }
  
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
    throw new ActionNotSupportedException("Order delete is not supported (yet)");  
  }

  @Override
  public List<Product> getPaginated(Pageable paging) {
    throw new ActionNotSupportedException("Order delete is not supported (yet)");  
  }
  
  @Override
  public Product get(Long key) {
    return productRepository.findById(key)
        .orElseThrow(() -> new NoItemFoundException("No Product found"));
  }
  
  public List<Product> getAllByType(Long type) {
    return productRepository.findByProductType(type);
  }

  public List<Product> getAllByTypePaginated(Long type, Pageable paging) {
    return productRepository.findByProductType(type, paging).getContent();
  }

}
