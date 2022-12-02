package com.products.config;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import com.productsenums.ProductType;

@Component
public class ProductTypeConverter implements Converter<String, ProductType> {
  @Override
  public ProductType convert(String source) {
    return ProductType.valueOf(source.toUpperCase());
  }
}
