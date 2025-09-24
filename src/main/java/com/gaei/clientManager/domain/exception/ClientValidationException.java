package com.gaei.clientManager.domain.exception;

public class ClientValidationException extends IllegalArgumentException{
    private final String idTx;

    public ClientValidationException(String message, String idTx){
        super(message);
        this.idTx = idTx;
    }

    public String getIdTx(){
        return idTx;
    }
}
