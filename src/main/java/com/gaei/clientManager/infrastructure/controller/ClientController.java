package com.gaei.clientManager.infrastructure.controller;

import com.gaei.clientManager.application.ports.in.CreateClientUseCase;
import com.gaei.clientManager.application.ports.in.FindClientUseCase;
import com.gaei.clientManager.domain.model.Client;
import com.gaei.clientManager.infrastructure.persistence.dto.ClientFoundResponseDTO;
import com.gaei.clientManager.infrastructure.persistence.dto.ClientRequestDTO;
import com.gaei.clientManager.infrastructure.persistence.dto.ClientResponseDTO;
import com.gaei.clientManager.infrastructure.persistence.mapper.ClientMapper;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "client", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class ClientController {

    private final CreateClientUseCase createClientUseCase;
    private final FindClientUseCase findClientUseCase;

    @PostMapping("/guardarCliente")
    public ResponseEntity<ClientResponseDTO> createClient(@RequestBody ClientRequestDTO requestDTO){
        Client clientMapper = ClientMapper.toApplication(requestDTO);
        Client clientCreated = createClientUseCase.saveClient(clientMapper);
        ClientResponseDTO responseDTO = ClientMapper.toResponse(clientCreated);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @GetMapping("/consultarCliente/{document}")
    public ResponseEntity<ClientFoundResponseDTO> findClient(@PathVariable String document){
        String[] documents = document.split("_");
        if(documents.length != 2){
            throw new IllegalArgumentException("El formato del documento debe ser tipo de documento y número de documento");
        }
        String documentType = documents[0];
        String documentNumber = documents[1];
        Client client = findClientUseCase.findClient(documentNumber,documentType);
        ClientFoundResponseDTO responseDTO = ClientMapper.toFoundResponse(client);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }
}
