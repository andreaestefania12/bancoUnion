package com.gaei.clientManager.infrastructure.persistence.mapper;

import com.gaei.clientManager.domain.model.Client;
import com.gaei.clientManager.domain.model.DocumentType;
import com.gaei.clientManager.infrastructure.persistence.dto.ClientFoundResponseDTO;
import com.gaei.clientManager.infrastructure.persistence.dto.ClientRequestDTO;
import com.gaei.clientManager.infrastructure.persistence.dto.ClientResponseDTO;
import com.gaei.clientManager.infrastructure.persistence.entity.ClientEntity;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.UUID;

@Component
public class ClientMapper {

    public static Client toDomain(ClientEntity entity){
        Objects.requireNonNull(entity, "La entidad no puede ser vacía");
        return new Client(
                entity.getIdTx().toString(),
                DocumentType.from(entity.getDocumentType()),
                entity.getDocumentNumber(),
                entity.getFirstName(),
                entity.getMiddleName(),
                entity.getLastName(),
                entity.getSecondLastName(),
                entity.getPhoneNumber(),
                entity.getEmail()
        );
    }

    public static ClientEntity toEntity(Client client){
        Objects.requireNonNull(client, "El modelo del dominio no puede ser vacio");
        return new ClientEntity(
                client.getIdTx() != null ? UUID.fromString(client.getIdTx()) : null,
                client.getDocumentType().getType(),
                client.getDocumentNumber(),
                client.getFirstName(),
                client.getMiddleName(),
                client.getLastName(),
                client.getSecondLastName(),
                client.getPhoneNumber(),
                client.getEmail()
        );
    }

    public static Client toApplication(ClientRequestDTO requestDTO){
        Objects.requireNonNull(requestDTO, "El request dto no puede ser vacio");
        return new Client(
                requestDTO.idTx() != null ? requestDTO.idTx() : null,
                DocumentType.from(requestDTO.documentType()),
                requestDTO.documentNumber(),
                requestDTO.firstName(),
                requestDTO.middleName(),
                requestDTO.lastName(),
                requestDTO.secondLastName(),
                requestDTO.phoneNumber(),
                requestDTO.email()
        );
    }

    public static ClientResponseDTO toResponse(Client client, String action){
        Objects.requireNonNull(client, "El cliente no puede ser vacio");
        String documentNumber = client.getDocumentNumber();
        String message = "Cliente " + documentNumber + " " + action + " de forma exitosa";
        return new ClientResponseDTO(
                client.getIdTx(),
                message
        );
    }

    public static ClientFoundResponseDTO toFoundResponse(Client client){
        return new ClientFoundResponseDTO(
                client.getDocumentType().getType(),
                client.getDocumentNumber(),
                client.getFirstName(),
                client.getMiddleName(),
                client.getLastName(),
                client.getSecondLastName(),
                client.getPhoneNumber(),
                client.getEmail()
        );
    }
}
