package com.gaei.clientManager.domain.service;

import com.gaei.clientManager.domain.model.Client;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class ClientValidatorService {
    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");

    /**
     * Method para la validación de campos obligatorios
     * @param client
     */
    public void validateClient(Client client){
        List<String> errors = new ArrayList<>();

        if (client.getIdTx() == null) {
            errors.add("idTx");
        }
        if (client.getDocumentType() == null) {
            errors.add("tipo documento");
        }
        if (client.getDocumentNumber() == null) {
            errors.add("número de documento");
        }
        if (client.getFirstName() == null) {
            errors.add("primer nombre");
        }
        if (client.getLastName() == null) {
            errors.add("primer apellido");
        }
        if (client.getPhoneNumber() == null) {
            errors.add("teléfono");
        }
        if (client.getEmail() == null) {
            errors.add("correo");
        }

        if(!errors.isEmpty()){
            String message = "Campos " + String.join(", ", errors)+ ". Son obligatirios.";
            throw new IllegalArgumentException(message);
        }
    }

    /**
     * Method para la validación de formato email
     * @param email
     */
    public void validateEmail(String email){
        if(!EMAIL_PATTERN.matcher(email).matches()){
            throw new IllegalArgumentException("El formato del correo electronico no es válido");
        }
    }
}
