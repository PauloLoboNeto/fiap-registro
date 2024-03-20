package com.fiap.authenticacao.domain.ports.out;

import com.fiap.authenticacao.domain.model.Ponto;

import java.util.Optional;

public interface IPontoRepositoryPort {
    Optional<Ponto> registrar(String usuario, String matricula, String email);
}
