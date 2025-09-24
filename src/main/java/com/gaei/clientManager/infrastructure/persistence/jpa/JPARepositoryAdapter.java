package com.gaei.clientManager.infrastructure.persistence.jpa;

import com.gaei.clientManager.application.ports.out.ClientRepository;
import com.gaei.clientManager.domain.model.Client;
import com.gaei.clientManager.infrastructure.persistence.entity.ClientEntity;
import com.gaei.clientManager.infrastructure.persistence.mapper.ClientMapper;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public class JPARepositoryAdapter implements ClientRepository {

    private final JPARepository jpaRepository;

    public JPARepositoryAdapter(JPARepository jpaRepository){
        this.jpaRepository = jpaRepository;
    }

    @Override
    public Client save(Client client){
        ClientEntity clientEntity = ClientMapper.toEntity(client);
        ClientEntity saved = jpaRepository.save(clientEntity);
        return ClientMapper.toDomain(saved);
    }

    @Override
    public Optional<Client> findByDocumentNumberAndType(String documentNumber, String documentType){
        Optional<ClientEntity> findClient = jpaRepository.findByDocumentNumberAndDocumentType(documentNumber,documentType);
        return findClient.map(ClientMapper::toDomain);
    }

    @Override
    public Optional<Client> findById(String idTx){
        Optional<ClientEntity> findClient = jpaRepository.findById(UUID.fromString(idTx));
        return findClient.map(ClientMapper::toDomain);
    }
}
