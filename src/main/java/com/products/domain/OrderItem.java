package com.products.domain;

import java.math.BigDecimal;
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
import javax.validation.constraints.Min;

@Entity
@Table(name = "order_item")
public class OrderItem {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "product_id", nullable = false)
  private Long productId;

  @Column(name = "product_desription", nullable = false, length = 45)
  private String productDescription;

  @Min(value = 1)
  private Long quantity;

  @Min(value = 0L)
  @Column(name = "price", precision = 11, scale = 2, nullable = false)
  private BigDecimal price;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "order_Id", referencedColumnName = "id")
  private Orders order;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Long getProductId() {
    return productId;
  }

  public void setProductId(Long productId) {
    this.productId = productId;
  }

  public String getProductDescription() {
    return productDescription;
  }

  public void setProductDescription(String productDescription) {
    this.productDescription = productDescription;
  }

  public Long getQuantity() {
    return quantity;
  }

  public void setQuantity(Long quantity) {
    this.quantity = quantity;
  }

  public Orders getOrder() {
    return order;
  }

  public void setOrder(Orders order) {
    this.order = order;
  }

  public BigDecimal getPrice() {
    return price;
  }

  public void setPrice(BigDecimal price) {
    this.price = price;
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
    OrderItem other = (OrderItem) obj;
    return Objects.equals(id, other.id);
  }

  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder();
    builder.append("OrderItem [id=");
    builder.append(id);
    builder.append(", productId=");
    builder.append(productId);
    builder.append(", productDescription=");
    builder.append(productDescription);
    builder.append(", quantity=");
    builder.append(quantity);
    builder.append(", price=");
    builder.append(price);
    builder.append(", order=");
    builder.append(order);
    builder.append("]");
    return builder.toString();
  }

  
  public static class Builder {
    private Long productId;
    private String productDescription;
    private Long quantity;
    private BigDecimal price;
    
    public OrderItem build() {
      OrderItem res = new OrderItem();
      res.productDescription = this.productDescription;
      res.quantity = this.quantity;
      res.productId = this.productId;
      res.price = this.price;
      return res;
    }
    public Builder setProductId(Long productId) {
      this.productId = productId;
      return this;
    }
    public Builder setProductDescription(String productDescription) {
      this.productDescription = productDescription;
      return this;
    }
    public Builder setQuantity(Long quantity) {
      this.quantity = quantity;
      return this;
    }
    public Builder setPrice(BigDecimal price) {
      this.price = price;
      return this;
    }
    
  }

}
