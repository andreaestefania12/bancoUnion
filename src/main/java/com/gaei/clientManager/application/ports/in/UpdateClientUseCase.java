package com.gaei.clientManager.application.ports.in;

import com.gaei.clientManager.domain.model.Client;

public interface UpdateClientUseCase {
    Client updateClient(Client client);
}
