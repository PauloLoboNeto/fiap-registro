package com.fiap.authenticacao.application.web;

import com.fiap.authenticacao.domain.model.Ponto;
import com.fiap.authenticacao.domain.ports.in.IPontoUseCasePort;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/registro")
@RequiredArgsConstructor
public class PontoControllerAdapter {
    private final IPontoUseCasePort pontoUseCasePort;

    @PostMapping(value = {"/", ""}, produces = "application/json")
    public ResponseEntity<?> registro() {
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();

        Optional<Ponto> ponto = pontoUseCasePort.registrar("user.test", "123", "usuario@email.com");
        return ponto.isPresent() ? ResponseEntity.ok(ponto.get()) : ResponseEntity.notFound().build();
    }
}
