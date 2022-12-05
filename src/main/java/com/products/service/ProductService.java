package com.products.service;

import java.util.Objects;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import com.products.domain.ProductDetails;
import com.products.dto.ProductDetailsDto;
import com.products.dto.ProductDetailsResponseDto;
import com.products.dto.ProductResponseDto;
import com.products.service.dao.ProductDaoService;
import com.products.service.dao.ProductDetailsDaoService;
import com.products.service.mapper.ProductMapper;
import com.products.vo.RetrieveProductsRequest;

@Service
public class ProductService {

  private static final Logger logger = LoggerFactory.getLogger(ProductService.class);


  private ProductDaoService productDaoService;
  private ProductDetailsDaoService productDetailsDaoService;
  private ProductMapper productMapper;

  public ProductService(
      @Qualifier("productDaoService") ProductDaoService productDaoService,
      @Qualifier("productDetailsDaoService") ProductDetailsDaoService productDetailsDaoService,
      ProductMapper productMapper) {
    this.productDaoService = productDaoService;
    this.productDetailsDaoService = productDetailsDaoService;
    this.productMapper = productMapper;
  }

  @Cacheable(key = "#request", value = "products")
  public ProductResponseDto getProducts(RetrieveProductsRequest request) {
    logger.info("{}", request);
    if (Objects.nonNull(request.getPage()) && Objects.nonNull(request.getSize())) {
      return new ProductResponseDto(productMapper.map(productDaoService.getAllByTypePaginated(
          request.getType(), PageRequest.of(request.getPage(), request.getSize()))));
    }
    return new ProductResponseDto(
        productMapper.map(productDaoService.getAllByType(request.getType())));
  }
  

  public void deleteDetails(Long id) {
    logger.info("delete {}", id);
    productDetailsDaoService.delete(id);
  }
  
  public void updateProductDetails(ProductDetailsDto productDetailsDto, Long id) {
    logger.info("update {} {} ", id, productDetailsDto);
    ProductDetails productDetails = productDetailsDaoService.get(id);
    productMapper.merge(productDetails, productDetailsDto);
    productDetailsDaoService.save(productDetails);
  }
  
  public ProductDetailsResponseDto getProductDetails(Long id) {
    logger.info("getDetailsForProduct {}", id);
    return new ProductDetailsResponseDto (productMapper.mapDetailsCollection(productDetailsDaoService.getAll(id)));
  }

}
