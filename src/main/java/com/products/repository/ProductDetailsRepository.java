package com.products.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import com.products.domain.ProductDetails;

@Repository

public interface ProductDetailsRepository extends PagingAndSortingRepository<ProductDetails, Long> {
  @Modifying
  @Query(value = "DELETE FROM product_details t WHERE t.id = ?1", nativeQuery = true)
  void deleteById(Long id);
}
