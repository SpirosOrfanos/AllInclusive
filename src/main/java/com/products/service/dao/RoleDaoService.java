package com.products.service.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.products.domain.Role;
import com.products.repository.RoleRepository;

@Service("roleDaoService")
@Transactional(readOnly = true)
public class RoleDaoService {
  @Autowired
  private RoleRepository roleDao;

  public Role findByName(String name) {
    Role role = roleDao.findRoleByName(name);
    return role;
  }
}
