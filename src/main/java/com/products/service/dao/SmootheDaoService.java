package com.products.service.dao;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.products.domain.ProductDetails;
import com.products.domain.Smoothe;
import com.products.exception.ActionNotSupportedException;
import com.products.exception.NoItemFoundException;
import com.products.repository.SmootheRepository;

@Transactional(readOnly = true)
@Service("smootheDaoService")
public class SmootheDaoService implements DaoService<Smoothe, Long> {

  private SmootheRepository smootheRepository;

  @Override
  public void update(Smoothe product) {
    throw new ActionNotSupportedException("Order update is not supported (yet)");
  }

  @Override
  public void delete(Long id) {
    throw new ActionNotSupportedException("Order delete is not supported (yet)");

  }

  @Override
  public Smoothe save(Smoothe product) {
    throw new ActionNotSupportedException("Order save is not supported (yet)");
  }

  @Override
  public List<Smoothe> getAll() {
    return StreamSupport.stream(smootheRepository.findAll().spliterator(), false)
        .collect(Collectors.toList());
  }

  @Override
  public List<Smoothe> getPaginated(Pageable paging) {
    return StreamSupport.stream(smootheRepository.findAll().spliterator(), false)
        .collect(Collectors.toList());
  }
  
  @Override
  public Smoothe get(Long key) {
    return smootheRepository.findById(key)
        .orElseThrow(() -> new NoItemFoundException("No Smoothe found"));
  }

}
