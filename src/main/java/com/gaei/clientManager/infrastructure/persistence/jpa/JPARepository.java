package com.gaei.clientManager.infrastructure.persistence.jpa;

import com.gaei.clientManager.infrastructure.persistence.entity.ClientEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JPARepository extends JpaRepository<ClientEntity, String> {
    Optional<ClientEntity> findByDocumentNumberAndDocumentType(String documentNumber, String documentTyper);
}
