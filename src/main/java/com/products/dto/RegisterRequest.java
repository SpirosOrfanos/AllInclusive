package com.products.dto;

import java.io.Serializable;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import com.products.enums.RoleTypeEnum;

public class RegisterRequest implements Serializable{
  @NotEmpty
  private String username;
  @NotEmpty
  @Min(value = 0, message = "Minimum 8 characters")
  private String password;
  @NotEmpty
  private RoleTypeEnum role;
  public String getUsername() {
    return username;
  }
  public void setUsername(String username) {
    this.username = username;
  }
  public String getPassword() {
    return password;
  }
  public void setPassword(String password) {
    this.password = password;
  }
  public RoleTypeEnum getRole() {
    return role;
  }
  public void setRole(RoleTypeEnum role) {
    this.role = role;
  }

}
