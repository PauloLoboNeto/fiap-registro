package com.fiap.authenticacao.application.web.configuration;

import com.fiap.authenticacao.domain.ports.in.IPontoUseCasePort;
import com.fiap.authenticacao.domain.ports.out.IPontoRepositoryPort;
import com.fiap.authenticacao.domain.usecase.PontoUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigurationContext {
    @Bean
    public IPontoUseCasePort pontoUseCasePort(IPontoRepositoryPort pontoRepositoryPort) {
        return new PontoUseCase(pontoRepositoryPort);
    }
}
