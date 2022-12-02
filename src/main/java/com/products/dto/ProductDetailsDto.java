package com.products.dto;

import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_EMPTY)
public class ProductDetailsDto implements Serializable {
  private Long id;
  private String description;
  private String detaiInfo;
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
  public String getDetaiInfo() {
    return detaiInfo;
  }
  public void setDetaiInfo(String detaiInfo) {
    this.detaiInfo = detaiInfo;
  }
  
  
}
