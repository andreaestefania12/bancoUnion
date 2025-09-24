package com.gaei.clientManager.infrastructure.controller;

import com.gaei.clientManager.application.ports.in.CreateClientUseCase;
import com.gaei.clientManager.domain.model.Client;
import com.gaei.clientManager.infrastructure.persistence.dto.ClientRequestDTO;
import com.gaei.clientManager.infrastructure.persistence.dto.ClientResponseDTO;
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
    public ResponseEntity<ClientResponseDTO> createClient(@RequestBody ClientRequestDTO requestDTO){
        Client clientMapper = ClientMapper.toApplication(requestDTO);
        Client clientCreated = createClientUseCase.saveClient(clientMapper);
        ClientResponseDTO responseDTO = ClientMapper.toResponse(clientCreated);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }
}
