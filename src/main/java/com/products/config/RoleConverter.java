package com.products.config;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import com.products.enums.ProductType;

@Component
public class RoleConverter implements Converter<String, ProductType> {
  @Override
  public ProductType convert(String source) {
    return ProductType.valueOf(source.toUpperCase());
  }
}
