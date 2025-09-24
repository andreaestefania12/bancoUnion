package com.gaei.clientManager.application.service;

import com.gaei.clientManager.application.ports.in.CreateClientUseCase;
import com.gaei.clientManager.application.ports.out.ClientRepository;
import com.gaei.clientManager.domain.model.Client;
import com.gaei.clientManager.domain.service.ClientValidatorService;
import org.springframework.stereotype.Service;

@Service
public class ClientService implements CreateClientUseCase {

    private final ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository){
        this.clientRepository = clientRepository;
    }

    @Override
    public Client saveClient(Client client){
        ClientValidatorService validatorService = new ClientValidatorService();
        validatorService.validateClient(client);
        validatorService.validateEmail(client.getEmail());
        return clientRepository.save(client);
    }
}
