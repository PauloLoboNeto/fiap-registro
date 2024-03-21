package com.fiap.registro.application.web.configuration;

import com.fiap.registro.domain.ports.in.IPontoUseCasePort;
import com.fiap.registro.domain.ports.out.IAtualizaRegistroPontoQueuePort;
import com.fiap.registro.domain.ports.out.IPontoRepositoryPort;
import com.fiap.registro.domain.usecase.PontoUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigurationContext {
    @Bean
    public IPontoUseCasePort pontoUseCasePort(IPontoRepositoryPort pontoRepositoryPort, IAtualizaRegistroPontoQueuePort atualizaRegistroPontoQueuePort) {
        return new PontoUseCase(pontoRepositoryPort, atualizaRegistroPontoQueuePort);
    }
}
