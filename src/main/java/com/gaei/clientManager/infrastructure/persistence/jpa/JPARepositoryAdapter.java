package com.gaei.clientManager.infrastructure.persistence.jpa;

import com.gaei.clientManager.application.ports.out.ClientRepository;
import com.gaei.clientManager.domain.model.Client;
import com.gaei.clientManager.infrastructure.persistence.entity.ClientEntity;
import com.gaei.clientManager.infrastructure.persistence.mapper.ClientMapper;
import org.springframework.stereotype.Repository;

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
}
