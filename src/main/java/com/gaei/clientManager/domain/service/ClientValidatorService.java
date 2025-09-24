package com.gaei.clientManager.domain.service;

import com.gaei.clientManager.domain.exception.ClientValidationException;
import com.gaei.clientManager.domain.model.Client;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class ClientValidatorService {
    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");

    /**
     * Method para la validación de campos obligatorios y la validación de formato email
     * @param client
     */
    public void validateClient(Client client){
        List<String> errors = new ArrayList<>();
        List<String> requiredFields = new ArrayList<>();

        if (client.getIdTx() == null || client.getIdTx().trim().isEmpty()) {
            requiredFields.add("idTx");
        }
        if (client.getDocumentType() == null) {
            requiredFields.add("tipo documento");
        }
        if (client.getDocumentNumber() == null || client.getDocumentNumber().trim().isEmpty()) {
            requiredFields.add("número de documento");
        }
        if (client.getFirstName() == null || client.getFirstName().trim().isEmpty()) {
            requiredFields.add("primer nombre");
        }
        if (client.getLastName() == null || client.getLastName().trim().isEmpty()) {
            requiredFields.add("primer apellido");
        }
        if (client.getPhoneNumber() == null) {
            requiredFields.add("teléfono");
        }
        if (client.getEmail() == null || client.getEmail().trim().isEmpty()) {
            requiredFields.add("correo electrónico");
        }

        if(!requiredFields.isEmpty()){
            String fieldsMessage =  "Campos " + String.join(", ", requiredFields)+ ". Son obligatirios.";
            errors.add(fieldsMessage);
        }

        if (client.getEmail() != null && !EMAIL_PATTERN.matcher(client.getEmail()).matches()) {
            errors.add("Campo Correo electrónico, no cumple con la estructura de un correo electrónico valido");
        }

        if(!errors.isEmpty() ){
            String fullMessage = String.join(" ", errors);
            throw new ClientValidationException(fullMessage, client.getIdTx());
        }
    }
}
