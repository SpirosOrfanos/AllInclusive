package com.products.service;

import java.util.Objects;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import com.products.dto.ProductResponseDto;
import com.products.service.dao.ProductDaoService;
import com.products.service.mapper.ProductMapper;
import com.products.vo.RetrieveProductsRequest;

@Service
public class ProductService {

  private static final Logger logger = LoggerFactory.getLogger(ProductService.class);


  private ProductDaoService productDaoService;
  private ProductMapper productMapper;

  public ProductService(@Qualifier("productDaoService") ProductDaoService productDaoService,
      ProductMapper productMapper) {
    this.productDaoService = productDaoService;
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

}
