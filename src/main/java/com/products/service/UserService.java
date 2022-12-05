package com.products.service;

import java.util.HashSet;
import java.util.Set;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.products.domain.Role;
import com.products.domain.User;
import com.products.dto.RegisterRequest;
import com.products.exception.UserAlreadyExistsException;
import com.products.service.dao.RoleDaoService;
import com.products.service.dao.UserDaoService;

@Service(value = "userService")
public class UserService implements UserDetailsService {
  private static final Logger logger = LoggerFactory.getLogger(UserService.class);

  @Autowired
  private UserDaoService userDaoService;
  
  @Autowired
  private RoleDaoService roleDaoService;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    User user = userDaoService.findByUsername(username);
    logger.info("{}", user);
    if (user == null) {
      throw new UsernameNotFoundException("Invalid username or password.");
    }
    return new org.springframework.security.core.userdetails.User(user.getUsername(),
        user.getPassword(), getAuthority(user));
  }

  private Set<SimpleGrantedAuthority> getAuthority(User user) {
    Set<SimpleGrantedAuthority> authorities = new HashSet<>();
    user.getRoles().forEach(role -> {
      authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getName()));
    });
    return authorities;
  }
  
  public Long save(RegisterRequest req) throws UserAlreadyExistsException {
    if (null!= userDaoService.findByUsername(req.getUsername())) {
      throw new UserAlreadyExistsException("user exists");
    }
  
    return userDaoService.save(req).getId();
  }

    

}
