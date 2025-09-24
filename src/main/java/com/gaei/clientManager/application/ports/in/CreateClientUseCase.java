package com.gaei.clientManager.application.ports.in;

import com.gaei.clientManager.domain.model.Client;

public interface CreateClientUseCase {
    Client saveClient(Client client);
}
