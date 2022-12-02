package com.products.domain;

import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "product_details")
public class ProductDetails {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "description", nullable = false, length = 45)
  private String description;

  @Column(name = "detail_info", nullable = false, length = 45)
  private String detaiInfo;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "product_Id", referencedColumnName = "id")
  private Product product;

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

  public Product getProduct() {
    return product;
  }

  public void setProduct(Product product) {
    this.product = product;
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    ProductDetails other = (ProductDetails) obj;
    return Objects.equals(id, other.id);
  }

  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder();
    builder.append("ProductDetails [id=");
    builder.append(id);
    builder.append(", description=");
    builder.append(description);
    builder.append(", detaiInfo=");
    builder.append(detaiInfo);
    builder.append(", product=");
    builder.append(product);
    builder.append("]");
    return builder.toString();
  }



}
