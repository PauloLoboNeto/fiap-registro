package com.fiap.authenticacao.infrastructure.persistence;


import com.fiap.authenticacao.domain.model.Ponto;
import com.fiap.authenticacao.domain.ports.out.IPontoRepositoryPort;
import com.fiap.authenticacao.infrastructure.persistence.entity.PontoEntity;
import com.fiap.authenticacao.infrastructure.persistence.repository.PontoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PontoRepositoryAdapter implements IPontoRepositoryPort {
    private final PontoRepository repository;

    @Override
    public Optional<Ponto> registrar(String usuario, String matricula, String email) {
        PontoEntity ponto = PontoEntity.builder()
                .usuario(usuario)
                .matricula(matricula)
                .email(email)
                .build();
        return Optional.of(new PontoEntity().to(repository.save(ponto)));
    }
}
