package com.fiap.registro.domain.model;

import lombok.*;

import java.util.UUID;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class Usuario {
    private UUID id;
    private String nome;
    private String matricula;
    private String email;
}
