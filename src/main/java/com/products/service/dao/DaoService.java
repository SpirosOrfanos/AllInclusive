package com.products.service.dao;

import java.util.List;
import org.springframework.data.domain.Pageable;

public interface DaoService<T, K> {
  
  public void update(T t);
  public void delete(K k);
  public T save(T t);
  public T get(K k);
  public List<T> getAll();
  public List<T> getPaginated(Pageable paging);

}
