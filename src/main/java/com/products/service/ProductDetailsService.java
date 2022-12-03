package com.products.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import com.products.domain.ProductDetails;
import com.products.dto.ProductDetailsDto;
import com.products.dto.ProductDetailsResponseDto;
import com.products.service.dao.ProductDetailsDaoService;
import com.products.service.mapper.ProductMapper;

@Service
public class ProductDetailsService {

  private static final Logger logger = LoggerFactory.getLogger(ProductDetailsService.class);

  
  private ProductDetailsDaoService productDetailsDaoService;
  private ProductMapper productMapper;
  
  public ProductDetailsService(
      ProductMapper productMapper,
      @Qualifier("productDetailsDaoService") ProductDetailsDaoService productDetailsDaoService) {
   this.productMapper = productMapper;
   this.productDetailsDaoService = productDetailsDaoService;
  }
  
  public void delete(Long id) {
    logger.info("delete {}", id);
    productDetailsDaoService.delete(id);
  }
  
  public void update(ProductDetailsDto productDetailsDto, Long id) {
    logger.info("update {} {} ", id, productDetailsDto);
    ProductDetails productDetails = productDetailsDaoService.get(id);
    productMapper.merge(productDetails, productDetailsDto);
    productDetailsDaoService.save(productDetails);
  }
  
  public ProductDetailsResponseDto getDetailsForProduct(Long id) {
    logger.info("getDetailsForProduct {}", id);
    return new ProductDetailsResponseDto (productMapper.mapDetailsCollection(productDetailsDaoService.getAll(id)));
  }
  
}
