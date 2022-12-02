package com.products.dto;

import java.io.Serializable;
import java.util.List;

public class SmootheDto implements Serializable{
  private Long id;
  private String description;
  private List<ProductDetailsDto> details;
  public Long getId() {
    return id;
  }
  public void setId(Long id) {
    this.id = id;
  }
  public String getDescription() {
    return description;
  }
  public void setDescription(String description) {
    this.description = description;
  }
  public List<ProductDetailsDto> getDetails() {
    return details;
  }
  public void setDetails(List<ProductDetailsDto> details) {
    this.details = details;
  }

}
