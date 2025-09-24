package com.gaei.clientManager.infrastructure.persistence.mapper;

import com.gaei.clientManager.domain.model.Client;
import com.gaei.clientManager.domain.model.DocumentType;
import com.gaei.clientManager.infrastructure.persistence.entity.ClientEntity;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class ClientMapper {

    public static Client toDomain(ClientEntity entity){
        Objects.requireNonNull(entity, "La entidad no puede ser vacía");
        return new Client(
                entity.getIdTx(),
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
                client.getIdTx(),
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
