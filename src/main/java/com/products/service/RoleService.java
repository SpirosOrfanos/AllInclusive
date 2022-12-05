package com.products.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.products.domain.Role;
import com.products.service.dao.RoleDaoService;
@Service
public class RoleService {

  @Autowired
  private RoleDaoService roleDaoService;

  public Role findByName(String name) {
      Role role = roleDaoService.findByName(name);
      return role;
  }
}
