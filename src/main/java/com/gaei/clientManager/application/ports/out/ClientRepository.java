package com.gaei.clientManager.application.ports.out;

import com.gaei.clientManager.domain.model.Client;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository {
    Client save(Client client);
}
