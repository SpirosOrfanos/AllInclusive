package com.products.repository;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import java.math.BigDecimal;
import java.util.*;
import javax.validation.ConstraintViolationException;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.PageRequest;
import com.products.domain.Product;
import com.products.domain.ProductDetails;
import com.products.domain.Smoothe;
import com.products.repository.ProductRepository;

@Tag("ProductRepositoryTest")  
@DataJpaTest
public class ProductRepositoryTest {

  @Qualifier("productRepository")
  @Autowired
  ProductRepository productRepository;
  @Test
  public void testSaveProduct() {
    Product smoothe = new Smoothe();
    smoothe.setDescription("Vanilla");
    smoothe.setPrice(new BigDecimal("33"));
    Product product = productRepository.save(smoothe);
    
    assertEquals("Vanilla", product.getDescription());
    assertTrue(product instanceof Smoothe);
    Product smo = productRepository.findByIdAndProductType(product.getId(), 1L);
    assertTrue(smo instanceof Smoothe);
    
    assertEquals(1, productRepository.findByProductType(1L).size());
    smoothe = new Smoothe();
    smoothe.setDescription("Vanilla2");
    smoothe.setPrice(new BigDecimal("22.22"));
    productRepository.save(smoothe);
    smoothe = new Smoothe();
    smoothe.setDescription("Vanilla3");
    smoothe.setPrice(new BigDecimal("22.22"));
    productRepository.save(smoothe);
    

  
    assertEquals(3, productRepository.findByProductType(1L).size());
    assertEquals(2, productRepository.findByProductType(1L, PageRequest.of(0, 2)).getContent().size());
    assertEquals(1, productRepository.findByProductType(1L, PageRequest.of(1, 2)).getContent().size());
    
 
  }
  
  @Test
  public void testSaveProductWithNegativeValue() {
    Product smoothe = new Smoothe();
    smoothe.setDescription("Vanilla");
    smoothe.setPrice(new BigDecimal("-22.222222"));
    assertThrows(ConstraintViolationException.class, () -> productRepository.save(smoothe));
  }
  
  @Test
  public void testSaveProductWithoutDescription() {
    Product smoothe = new Smoothe();
    assertThrows(DataIntegrityViolationException.class, () -> productRepository.save(smoothe));
  }
  
  @Test
  public void testSaveProductWithDetails() {
    Product smoothe = new Smoothe();
    smoothe.setDescription("Vanilla");
    smoothe.setPrice(new BigDecimal("22"));
    
    ProductDetails productDetails1 = new ProductDetails();
    productDetails1.setDescription("kCal");
    productDetails1.setDetaiInfo("50000");
    
    ProductDetails productDetails2 = new ProductDetails();
    productDetails2.setDescription("color");
    productDetails2.setDetaiInfo("white");
   
    smoothe.setProductDetails(List.of(productDetails1, productDetails2));
    
    Product product = productRepository.save(smoothe);
    assertEquals(2, product.getProductDetails().size());
  }
  
 
}
