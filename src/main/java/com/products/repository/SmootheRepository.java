package com.products.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import com.products.domain.Smoothe;

@Repository
public interface SmootheRepository extends PagingAndSortingRepository<Smoothe, Long> {

}
