package com.gaei.clientManager.domain.exception;

public class ClientNotFoundException extends RuntimeException {
  public ClientNotFoundException(String message) {
    super(message);
  }
}
