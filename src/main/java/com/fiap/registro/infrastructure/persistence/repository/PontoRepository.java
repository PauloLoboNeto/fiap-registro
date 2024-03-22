package com.fiap.registro.infrastructure.persistence.repository;

import com.fiap.registro.domain.model.Ponto;
import com.fiap.registro.infrastructure.persistence.entity.PontoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface PontoRepository extends JpaRepository<PontoEntity, UUID> {
    List<PontoEntity> findPontoEntityByUsuarioEquals(String usuario);
}
