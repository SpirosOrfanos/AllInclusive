package com.products.exception;

public class ActionNotSupportedException extends RuntimeException {

  private static final long serialVersionUID = 1L;

  public ActionNotSupportedException(String message) {
    super(message);
  }

}
