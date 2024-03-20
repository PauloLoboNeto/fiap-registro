package com.fiap.authenticacao.domain.usecase;

import com.fiap.authenticacao.domain.model.Ponto;
import com.fiap.authenticacao.domain.ports.in.IPontoUseCasePort;
import com.fiap.authenticacao.domain.ports.out.IPontoRepositoryPort;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
public class PontoUseCase implements IPontoUseCasePort {
    private final IPontoRepositoryPort repository;

    @Override
    public Optional<Ponto> registrar(String usuario, String matricula, String email) {
        return repository.registrar(usuario, matricula, email);
    }
}
