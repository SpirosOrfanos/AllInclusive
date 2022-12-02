package com.products;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import java.math.BigDecimal;
import java.util.*;
import javax.persistence.EntityManager;
import javax.validation.ConstraintViolationException;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;
import com.products.domain.Product;
import com.products.domain.ProductDetails;
import com.products.domain.Smoothe;
import com.products.repository.ProductDetailsRepository;
import com.products.repository.ProductRepository;
import com.products.repository.SmootheRepository;

@Tag("ProductRepositoryTest")  
@DataJpaTest
public class ProductDetailsRepositoryTest {

  @Qualifier("productRepository")
  @Autowired
  ProductRepository productRepository;
  
  @Qualifier("productDetailsRepository")
  @Autowired
  ProductDetailsRepository productDetailsRepository;
  

  
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
    //Save product
    Product product = productRepository.save(smoothe);
    
    assertEquals(2, product.getProductDetails().size());
    assertNotNull(productDetailsRepository.findById(product.getProductDetails().get(0).getId()));
    
    //Update product details
    Optional<ProductDetails> proOptional = productDetailsRepository
    .findById(product.getProductDetails().get(0).getId());
    proOptional.ifPresent(pd -> {
      pd.setDetaiInfo("1000000");
      productDetailsRepository.save(pd);  
    });
    
    proOptional = productDetailsRepository
        .findById(product.getProductDetails().get(0).getId());
    assertEquals("1000000", proOptional.get().getDetaiInfo());
    
    assertEquals(2, productRepository.findById(product.getId()).get().getProductDetails().size());
    
    productRepository.findById(product.getId()).get().getProductDetails()
    .stream().forEach(i -> System.out.println(i.getId()));
    
    //productDetailsRepository.deleteById(product.getProductDetails().get(0).getId());
    //productDetailsRepository.deleteById(product.getProductDetails().get(0).getId());
    
   // assertEquals(1, productRepository.findById(product.getId()).get().getProductDetails().size());
    
  }
  
 
}
