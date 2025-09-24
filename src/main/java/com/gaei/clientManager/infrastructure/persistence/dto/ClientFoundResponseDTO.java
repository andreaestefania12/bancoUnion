package com.gaei.clientManager.infrastructure.persistence.dto;

public record ClientFoundResponseDTO (
        String documentType,
        String documentNumber,
        String firstName,
        String middleName,
        String lastName,
        String secondLastName,
        Integer phoneNumber,
        String email
){
}
