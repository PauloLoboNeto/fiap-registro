package com.fiap.registro.domain.ports.out;

import com.fiap.registro.domain.model.Ponto;

import java.util.List;
import java.util.Optional;

public interface IPontoRepositoryPort {
    Optional<Ponto> registrar(String usuario, String matricula, String email);
    List<Ponto> obterRegistrosPorUsuario(String usuario);
}
