package com.products.service.dao;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.products.domain.Role;
import com.products.domain.User;
import com.products.dto.RegisterRequest;
import com.products.repository.RoleRepository;
import com.products.repository.UserRepository;

@Service("userDaoService")
@Transactional(readOnly = true)
public class UserDaoService {
  
  @Autowired
  private UserRepository userRepository;
  
  @Autowired
  private RoleRepository roleRepository;

  public User findByUsername(String username){
    return userRepository.findByUsername(username);
  }
  
  public List<User> findAll() {
    List<User> list = new ArrayList<>();
    userRepository.findAll().iterator().forEachRemaining(list::add);
    return list;
  }
  
  public User findOne(String username) {
    return userRepository.findByUsername(username);
  }
  @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
  public User save(RegisterRequest req) {
    
    Role role = roleRepository.findRoleByName(req.getRole().getId());
    User user = new User();
    user.setPassword(req.getPassword());
    user.setUsername(req.getUsername());
    user.setRoles(new HashSet<>());
    user.getRoles().add(role);
    
    return userRepository.save(user);
  }
}
