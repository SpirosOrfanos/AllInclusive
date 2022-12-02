package com.products.domain;

import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "orders")
public class Orders {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "order_dt")
  @Temporal(TemporalType.DATE)
  private java.util.Date datetime;

  @Enumerated(EnumType.ORDINAL)
  private Status status;

  @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<OrderItem> orderItems;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public java.util.Date getDatetime() {
    return datetime;
  }

  public void setDatetime(java.util.Date datetime) {
    this.datetime = datetime;
  }

  public List<OrderItem> getOrderItems() {
    return orderItems;
  }

  public void setOrderItems(List<OrderItem> orderItems) {
    this.orderItems = orderItems;
  }

  public Status getStatus() {
    return status;
  }

  public void setStatus(Status status) {
    this.status = status;
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
    Orders other = (Orders) obj;
    return Objects.equals(id, other.id);
  }

  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder();
    builder.append("Order [id=");
    builder.append(id);
    builder.append(", datetime=");
    builder.append(datetime);
    builder.append(", status=");
    builder.append(status);
    builder.append(", orderItems=");
    builder.append(orderItems);
    builder.append("]");
    return builder.toString();
  }

}
