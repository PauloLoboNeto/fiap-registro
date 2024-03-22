package com.fiap.registro.domain.usecase;

import com.fiap.registro.domain.model.Ponto;
import com.fiap.registro.domain.ports.in.IPontoUseCasePort;
import com.fiap.registro.domain.ports.out.IAtualizaRegistroPontoQueuePort;
import com.fiap.registro.domain.ports.out.IPontoRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class PontoUseCase implements IPontoUseCasePort {
    private final IPontoRepositoryPort repository;
    private final IAtualizaRegistroPontoQueuePort atualizaRegistroPontoQueuePort;

    @Override
    @Transactional
    public Optional<Ponto> registrar(String usuario, String matricula, String email) {
        Optional<Ponto> pontoRegistrado = repository.registrar(usuario, matricula, email);
        if (pontoRegistrado.isPresent()) {
            atualizaRegistroPontoQueuePort.publish(pontoRegistrado.get().toJsonStr());
        }
        return pontoRegistrado;
    }

    @Override
    public List<Ponto> obterRegistrosPorUsuario(String usuario) {
        return repository.obterRegistrosPorUsuario(usuario);
    }

}
