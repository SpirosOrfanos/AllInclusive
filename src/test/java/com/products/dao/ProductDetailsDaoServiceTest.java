package com.products.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import com.products.domain.ProductDetails;
import com.products.exception.NoItemFoundException;
import com.products.repository.ProductDetailsRepository;
import com.products.service.dao.ProductDetailsDaoService;

@Tag("ProductDetailsDaoServiceTest")
@SpringBootTest
public class ProductDetailsDaoServiceTest {

  private ProductDetailsRepository productDetailsRepository;
  private ProductDetailsDaoService productDetailsDaoService;
  private ProductDetails productDetails;
  private ProductDetails productDetailsSave;
  @BeforeEach
  public void before() {
    productDetailsRepository = mock(ProductDetailsRepository.class);
    productDetailsDaoService = new ProductDetailsDaoService(productDetailsRepository);  
    productDetails = new ProductDetails();
    productDetails.setId(1L);
    productDetails.setDescription("DES1");
    
    productDetailsSave = new ProductDetails();
    productDetailsSave.setDescription("DES1");
    
  }

  @Test
  public void save() {
    when(productDetailsRepository.save(productDetailsSave)).thenReturn(productDetails);
    assertEquals(1, productDetailsDaoService.save(productDetailsSave).getId());
  }



  @Test
  public void getPaginated() {
    when(productDetailsRepository.findAll()).thenReturn(List.of(productDetails));
    assertEquals(1, productDetailsDaoService.getPaginated(PageRequest.of(1, 2)).get(0).getId());
  }
  
  @Test
  public void get() {
    
    when(productDetailsRepository.findById(1L))
    .thenReturn(Optional.of(productDetails));
    when(productDetailsRepository.findById(2L))
    .thenThrow(new  NoItemFoundException("No order found"));
    assertEquals(1, productDetailsDaoService.get(1L).getId());
    assertThrows(NoItemFoundException.class, () -> productDetailsDaoService.get(2L));
  }
  
  @Test
  public void getAll() {
    when(productDetailsRepository.getAllForProduct(1L))
    .thenReturn(List.of(productDetails));
    assertEquals(1, productDetailsDaoService.getAll(1L).size());
  }
  
  
}
