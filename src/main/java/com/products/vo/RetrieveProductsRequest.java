package com.products.vo;

import java.io.Serializable;
import java.util.Objects;

public class RetrieveProductsRequest implements Serializable{

  private Integer page;
  private Integer size;
  private Long type;
  public Integer getPage() {
    return page;
  }
  public void setPage(Integer page) {
    this.page = page;
  }
  public Integer getSize() {
    return size;
  }
  public void setSize(Integer size) {
    this.size = size;
  }
  public Long getType() {
    return type;
  }
  public void setType(Long type) {
    this.type = type;
  }
  @Override
  public int hashCode() {
    return Objects.hash(page, size, type);
  }
  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    RetrieveProductsRequest other = (RetrieveProductsRequest) obj;
    return Objects.equals(page, other.page) && Objects.equals(size, other.size)
        && Objects.equals(type, other.type);
  }
  
  @Override
  public String toString() {
    StringBuilder builder2 = new StringBuilder();
    builder2.append("RetrieveProductsRequest [page=");
    builder2.append(page);
    builder2.append(", size=");
    builder2.append(size);
    builder2.append(", type=");
    builder2.append(type);
    builder2.append("]");
    return builder2.toString();
  }

  public static class Builder {
    private Integer page;
    private Integer size;
    private Long type;
    
    public RetrieveProductsRequest build() {
      RetrieveProductsRequest res = new RetrieveProductsRequest();
      res.page = this.page;
      res.size = this.size;
      res.type = this.type;
      return res;
    }
    
    public Builder setPage(Integer page) {
      this.page = page;
      return this;
    }
    public Builder setSize(Integer size) {
      this.size = size;
      return this;
    }
    public Builder setType(Long type) {
      this.type = type;
      return this;
    }
    
  }
  
}
