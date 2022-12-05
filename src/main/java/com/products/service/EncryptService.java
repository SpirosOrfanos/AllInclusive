package com.products.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.products.dto.RegisterRequest;

@Service
public class EncryptService {

  @Autowired
  private BCryptPasswordEncoder bcryptEncoder;
  
  public void encrypt(RegisterRequest user) {
    user.setPassword(bcryptEncoder.encode(user.getPassword()));
  }
}
