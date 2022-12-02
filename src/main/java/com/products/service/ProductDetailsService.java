package com.products.service;

import java.util.Objects;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import com.products.domain.ProductDetails;
import com.products.dto.ProductDetailsDto;
import com.products.dto.ProductDto;
import com.products.exception.NoItemFoundException;
import com.products.service.dao.ProductDetailsDaoService;
import com.products.service.dao.SmootheDaoService;
import com.products.service.mapper.ProductDetailMapper;
import com.productsenums.ProductType;

@Service
public class ProductDetailsService {

  private static final Logger logger = LoggerFactory.getLogger(ProductDetailsService.class);

  
  private ProductDetailsDaoService productDetailsDaoService;
  private SmootheDaoService smootheDaoService;
  private ProductDetailMapper productDetailMapper;
  
  public ProductDetailsService(
      ProductDetailMapper productDetailMapper,
      @Qualifier("productDetailsDaoService") ProductDetailsDaoService productDetailsDaoService) {
   this.productDetailMapper = productDetailMapper;
   this.productDetailsDaoService = productDetailsDaoService;
  }
  
  public void delete(Long id) {
    logger.info("delete {}", id);
    productDetailsDaoService.delete(id);
  }
  
  public void update(ProductDetailsDto productDetailsDto, Long id) {
    logger.info("update {} {} ", id, productDetailsDto);
    ProductDetails productDetails = productDetailsDaoService.get(id);
    productDetailMapper.merge(productDetails, productDetailsDto);
    productDetailsDaoService.save(productDetails);
  }
  
}
