package com.products.repository;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import com.products.domain.Product;

@Repository
public interface ProductRepository extends PagingAndSortingRepository<Product, Long> {

  @Query(
      value = "select * FROM product t WHERE t.id = ?1 and t.product_type = ?2", 
      nativeQuery = true)
  public Product findByIdAndProductType(Long id, Long productTypeId);
  
  @Query(
      value = "select * FROM product t WHERE t.product_type = ?1", 
      nativeQuery = true)
  public List<Product> findByProductType(Long productTypeId);
  
  @Query(
      value = "select * FROM product t WHERE t.product_type = ?1", 
      countQuery = "SELECT count(*) FROM product t WHERE t.product_type = ?1",
      nativeQuery = true)
  public Page<Product> findByProductType(Long productTypeId, Pageable paging);
}
