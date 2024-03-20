package com.fiap.authenticacao.infrastructure.persistence.repository;

import com.fiap.authenticacao.infrastructure.persistence.entity.PontoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PontoRepository extends JpaRepository<PontoEntity, UUID> {
}
