package com.products;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.math.BigDecimal;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.PageRequest;
import com.products.domain.Product;
import com.products.domain.Smoothe;
import com.products.repository.ProductRepository;
import com.products.repository.SmootheRepository;

@Tag("ProductRepositoryTest")  
@DataJpaTest
public class SmootheRepositoryTest {

  @Qualifier("smootheRepository")
  @Autowired
  SmootheRepository smootheRepository;
  
  @Qualifier("productRepository")
  @Autowired
  ProductRepository productRepository;
  
 

  @Test
  public void testSaveProductWithDetails() {
    
    Product gen = new Product();
    gen.setDescription("Generic");
    gen.setPrice(new BigDecimal("22"));
    productRepository.save(gen);
    
    Smoothe smoothe = new Smoothe();
    smoothe.setDescription("Vanilla");
    smoothe.setPrice(new BigDecimal("22"));
    
    Smoothe product = smootheRepository.save(smoothe);    
    
    smoothe = new Smoothe();
    smoothe.setDescription("Vanilla2");
    smoothe.setPrice(new BigDecimal("21"));   
    smootheRepository.save(smoothe);
    
    smoothe = new Smoothe();
    smoothe.setDescription("Vanilla3");
    smoothe.setPrice(new BigDecimal("21"));   
    smootheRepository.save(smoothe);
    assertEquals(3 , smootheRepository.findAll()
    .spliterator().estimateSize());
    assertEquals(4 , productRepository.findAll()
        .spliterator().estimateSize());
   
    assertEquals(2 ,smootheRepository.findAll(PageRequest.of(0, 2))
    .getContent().size());
    assertEquals(1 ,smootheRepository.findAll(PageRequest.of(1, 2))
        .getContent().size());
    
  }
  
 
}
