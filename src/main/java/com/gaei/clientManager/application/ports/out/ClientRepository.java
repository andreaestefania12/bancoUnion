package com.gaei.clientManager.application.ports.out;

import com.gaei.clientManager.domain.model.Client;

public interface ClientRepository {
    Client save(Client client);
}
