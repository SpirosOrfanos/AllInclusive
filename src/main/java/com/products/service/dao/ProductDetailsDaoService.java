package com.products.service.dao;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.products.domain.Product;
import com.products.domain.ProductDetails;
import com.products.exception.NoItemFoundException;
import com.products.repository.ProductDetailsRepository;

@Service("productDetailsDaoService")
@Transactional(readOnly = true)
public class ProductDetailsDaoService implements DaoService<ProductDetails, Long>{

  private ProductDetailsRepository productDetailsRepository;
  
  public ProductDetailsDaoService(ProductDetailsRepository productDetailsRepository) {
    this.productDetailsRepository = productDetailsRepository;
  }
  
  @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
  @Override
  public void update(ProductDetails productDetails) {
    productDetailsRepository.save(productDetails);
  }

  @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
  @Override
  public void delete(Long id) {
    productDetailsRepository.deleteById(id);
    
  }

  @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
  @Override
  public ProductDetails save(ProductDetails productDetails) {
    return productDetailsRepository.save(productDetails);
  }

  @Override
  public List<ProductDetails> getAll() {
    return StreamSupport.stream(productDetailsRepository.findAll()
        .spliterator(), false).collect(Collectors.toList());
  }

  @Override
  public List<ProductDetails> getPaginated(Pageable paging) {
    return StreamSupport.stream(productDetailsRepository.findAll()
        .spliterator(), false).collect(Collectors.toList());
  }
  
  @Override
  public ProductDetails get(Long key) {
    return productDetailsRepository.findById(key)
        .orElseThrow(() -> new NoItemFoundException("No Product Details found"));
  }

}
