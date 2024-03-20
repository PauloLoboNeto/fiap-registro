package com.fiap.authenticacao.domain.ports.in;

import com.fiap.authenticacao.domain.model.Ponto;

import java.util.Optional;

public interface IPontoUseCasePort {
    Optional<Ponto> registrar(String usuario, String matricula, String email);
}
