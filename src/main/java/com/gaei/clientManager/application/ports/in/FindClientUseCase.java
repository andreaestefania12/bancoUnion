package com.gaei.clientManager.application.ports.in;

import com.gaei.clientManager.domain.model.Client;

public interface FindClientUseCase {
    Client findClient(String documentNumber, String documentType);
}
