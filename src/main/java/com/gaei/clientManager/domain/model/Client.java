package com.gaei.clientManager.domain.model;

public final class Client {

    //ID del cliente
    private final String idTx;

    // Tipos de documento
    private final DocumentType documentType;

    // Número de documento
    private final String documentNumber;

    // Primer nombre
    private final String firstName;

    // Segundo nombre
    private final String middleName;

    // Primer apellido
    private final String lastName;

    // Segundo apellido
    private final String secondLastName;

    // Teléfono celular
    private final Integer phoneNumber;

    // Correo electrónico
    private final String email;

    public Client(
            String idTx,
            DocumentType documentType,
            String documentNumber,
            String firstName,
            String middleName,
            String lastName,
            String secondLastName,
            Integer phoneNumber,
            String email
    ){
        this.idTx = idTx;
        this.documentType = documentType;
        this.documentNumber = documentNumber;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.secondLastName = secondLastName;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    public String getIdTx() {
        return idTx;
    }

    public DocumentType getDocumentType() {
        return documentType;
    }

    public String getDocumentNumber() {
        return documentNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getSecondLastName() {
        return secondLastName;
    }

    public Integer getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    /**
     * Method para actualizar el cliente manteniendo el cliente existente
     * @param client
     * @return Client
     */
    public Client updateWith(Client client){
        return new Client(
                this.getIdTx(),
                client.getDocumentType(),
                client.getDocumentNumber(),
                client.getFirstName(),
                client.getMiddleName(),
                client.getLastName(),
                client.getSecondLastName(),
                client.phoneNumber,
                client.getEmail()
        );
    }
}
