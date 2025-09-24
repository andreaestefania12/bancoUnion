package com.gaei.clientManager.domain.exception;

public class ClientAlreadyExistsException extends RuntimeException {
    private final String idTx;

    public ClientAlreadyExistsException(String documentType, String documentNumber, String idTx) {
        super("Cliente "+ documentType + " " + documentNumber + ". Ya se encuentra registrado");
        this.idTx = idTx;
    }

    public String getIdTx() {
        return idTx;
    }
}
