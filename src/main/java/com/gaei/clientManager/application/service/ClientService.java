package com.gaei.clientManager.application.service;

import com.gaei.clientManager.application.ports.in.CreateClientUseCase;
import com.gaei.clientManager.application.ports.in.FindClientUseCase;
import com.gaei.clientManager.application.ports.out.ClientRepository;
import com.gaei.clientManager.domain.exception.ClientNotFoundException;
import com.gaei.clientManager.domain.exception.ClientValidationException;
import com.gaei.clientManager.domain.model.Client;
import com.gaei.clientManager.domain.service.ClientValidatorService;
import org.springframework.stereotype.Service;

@Service
public class ClientService implements CreateClientUseCase, FindClientUseCase {

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
        if(clientRepository.findByDocumentNumberAndType(documentNumber, documentType).isPresent()){
            String fullMessage = "Cliente "+ documentType + " " + documentNumber + ". Ya se encuentra registrado";
            throw new ClientValidationException(fullMessage, client.getIdTx());
        }
        return clientRepository.save(client);
    }

    @Override
    public Client findClient(String documentNumber, String documentType){
        String messageError = "Cliente "+ documentType + " " + documentNumber + ". No se encuentra registrado";
        return clientRepository.findByDocumentNumberAndType(documentNumber, documentType)
                .orElseThrow(() -> new ClientNotFoundException(messageError));
    }
}
