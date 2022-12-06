package com.products.dao;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import com.products.domain.Product;
import com.products.exception.ActionNotSupportedException;
import com.products.repository.ProductRepository;
import com.products.service.dao.ProductDaoService;

@Tag("ProductDaoServiceTest")
@SpringBootTest
class ProductDaoServiceTest {

  private ProductRepository productRepository;
  private ProductDaoService productDaoService;
  
  @BeforeEach
  public void before() {
    productRepository = mock(ProductRepository.class);
    productDaoService = new ProductDaoService(productRepository);  
  }
  
  
  void testGet() {
    Product prod = new Product();
    prod.setDescription("des1");
    prod.setId(1L);
    Product prod2 = new Product();
    prod2.setDescription("des2");
    
    Pageable pr = PageRequest.of(0, 1);
    Page<Product> empty = Page.empty();
    empty.and(prod);
    when(productRepository.findById(1L)).thenReturn(Optional.of(prod));
    when(productRepository.findByProductType(1L)).thenReturn(List.of(prod));
    when(productRepository.findByProductType(1L, pr)).thenReturn(empty);
    assertEquals(1L, productDaoService.get(1L).getId());
    assertEquals(1L, productDaoService.getAllByType(1L).get(0).getId());
    assertEquals(1L, productDaoService.getAllByTypePaginated(1L, pr).get(0).getId());
    
  }
  
  @Test
  void testSave() {
    assertThrows(ActionNotSupportedException.class, () -> productDaoService.save(new Product()));
  }
  
  @Test
  void testDelete() {
    assertThrows(ActionNotSupportedException.class, () -> productDaoService.delete(1L));
  }
  
  @Test
  void testUpdate() {
    assertThrows(ActionNotSupportedException.class, () -> productDaoService.update(new Product()));
  }
  
  @Test
  void testGetAll() {
    assertThrows(ActionNotSupportedException.class, () -> productDaoService.getAll());
  }
  
  @Test
  void testGetPaginated() {
    assertThrows(ActionNotSupportedException.class, () -> productDaoService.getPaginated(PageRequest.of(0, 1)));
  }
}
