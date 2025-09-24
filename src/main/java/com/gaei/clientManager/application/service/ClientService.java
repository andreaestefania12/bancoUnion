package com.gaei.clientManager.application.service;

import com.gaei.clientManager.application.ports.in.CreateClientUseCase;
import com.gaei.clientManager.application.ports.in.FindClientUseCase;
import com.gaei.clientManager.application.ports.in.UpdateClientUseCase;
import com.gaei.clientManager.application.ports.out.ClientRepository;
import com.gaei.clientManager.domain.exception.ClientAlreadyExistsException;
import com.gaei.clientManager.domain.exception.ClientNotFoundException;
import com.gaei.clientManager.domain.model.Client;
import com.gaei.clientManager.domain.service.ClientValidatorService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClientService implements CreateClientUseCase, FindClientUseCase, UpdateClientUseCase {

    private final ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository){
        this.clientRepository = clientRepository;
    }

    @Override
    public Client saveClient(Client client){
        ClientValidatorService validatorService = new ClientValidatorService();
        validatorService.validateClient(client);
        String documentType = client.getDocumentType().getType();
        String documentNumber = client.getDocumentNumber();
        Optional<Client> existingClient = clientRepository.findByDocumentNumberAndType(documentNumber, documentType);
        if(existingClient.isPresent()){
            throw new ClientAlreadyExistsException(documentType,documentNumber, existingClient.get().getIdTx());
        }
        return clientRepository.save(client);
    }

    @Override
    public Client findClient(String documentNumber, String documentType){
        return clientRepository.findByDocumentNumberAndType(documentNumber, documentType)
                .orElseThrow(() -> new ClientNotFoundException(documentType,documentNumber ));
    }

    @Override
    public Client updateClient(Client client){
        ClientValidatorService validatorService = new ClientValidatorService();
        validatorService.validateClient(client);
        String documentType = client.getDocumentType().getType();
        String documentNumber = client.getDocumentNumber();

        // Verificamos si el cliente existe con idTx ingresado,
        Client existingClient = clientRepository.findById(client.getIdTx())
                .orElseThrow(() -> new ClientNotFoundException(documentType, documentNumber));

        // Verificamos que no exista otro cliente con el mismo número y tipo de documento con un idTx diferente
        clientRepository.findByDocumentNumberAndType(documentNumber, documentType)
                .filter(clientDB -> !clientDB.getIdTx().equals(client.getIdTx())) // Validamos que sean diferentes ids
                .ifPresent(clientDB -> {
                    throw new ClientAlreadyExistsException(documentType, documentNumber, client.getIdTx());
                });

        Client updatedClient = existingClient.updateWith(client);
        return clientRepository.save(updatedClient);
    }
}
