package com.fiap.registro.infrastructure.persistence;


import com.fiap.registro.domain.model.Ponto;
import com.fiap.registro.domain.ports.out.IPontoRepositoryPort;
import com.fiap.registro.infrastructure.persistence.entity.PontoEntity;
import com.fiap.registro.infrastructure.persistence.repository.PontoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    @Override
    public List<Ponto> obterRegistrosPorUsuario(String usuario) {
        return repository.findPontoEntityByUsuarioEquals(usuario).stream().map(e -> new PontoEntity().to(e)).collect(Collectors.toList());
    }
}
