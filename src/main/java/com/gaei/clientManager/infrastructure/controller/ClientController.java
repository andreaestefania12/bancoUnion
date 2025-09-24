package com.gaei.clientManager.infrastructure.controller;

import com.gaei.clientManager.application.ports.in.CreateClientUseCase;
import com.gaei.clientManager.domain.model.Client;
import com.gaei.clientManager.infrastructure.persistence.entity.ClientEntity;
import com.gaei.clientManager.infrastructure.persistence.mapper.ClientMapper;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "client", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class ClientController {

    private final CreateClientUseCase createClientUseCase;

    @PostMapping
    public ResponseEntity<ClientEntity> createClient(@RequestBody Client client){
        Client clientCreate = createClientUseCase.saveClient(client);
        ClientEntity entity = ClientMapper.toEntity(clientCreate);
        return new ResponseEntity<>(entity, HttpStatus.OK);
    }
}
