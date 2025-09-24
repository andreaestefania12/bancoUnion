package com.gaei.clientManager.application.ports.out;

import com.gaei.clientManager.domain.model.Client;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientRepository {
    Client save(Client client);
    Optional<Client> findByDocumentNumberAndType ( String documentNumber, String documentType);
    Optional<Client> findById(String idTx);
}
