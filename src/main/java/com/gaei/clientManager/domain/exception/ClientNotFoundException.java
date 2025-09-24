package com.gaei.clientManager.domain.exception;

public class ClientNotFoundException extends RuntimeException {
  public ClientNotFoundException(String documentType, String documentNumber) {
      super("Cliente "+ documentType + " " + documentNumber + ". No se encuentra registrado");
  }
}
