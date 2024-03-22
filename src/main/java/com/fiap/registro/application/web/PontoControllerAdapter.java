package com.fiap.registro.application.web;

import com.fiap.registro.domain.model.Ponto;
import com.fiap.registro.domain.model.Usuario;
import com.fiap.registro.domain.ports.in.IPontoUseCasePort;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/registro")
@RequiredArgsConstructor
public class PontoControllerAdapter {
    private final IPontoUseCasePort pontoUseCasePort;

    @PostMapping(value = {"/", ""}, produces = "application/json")
    public ResponseEntity<?> registro(@AuthenticationPrincipal Usuario user) {

        Optional<Ponto> ponto = pontoUseCasePort.registrar(user.getNome(), user.getMatricula(), user.getEmail());
        return ponto.isPresent() ? ResponseEntity.ok(ponto.get()) : ResponseEntity.notFound().build();
    }

    @PutMapping(value = {"/", ""}, produces = "application/json")
    public ResponseEntity<?> atualizaRegistro() {
        return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body("Recurso não disponível!");
    }

    @GetMapping(value = {"/", ""}, produces = "application/json")
    public ResponseEntity<?> obterRegistrosPorUsuario(@AuthenticationPrincipal Usuario user) {
        List<Ponto> result = pontoUseCasePort.obterRegistrosPorUsuario(user.getNome());
        return ResponseEntity.ok(result);
    }
}
