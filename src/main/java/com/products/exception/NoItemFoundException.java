package com.products.exception;

public class NoItemFoundException extends RuntimeException {

  private static final long serialVersionUID = 1L;

  public NoItemFoundException(String message) {
    super(message);
  }

}
