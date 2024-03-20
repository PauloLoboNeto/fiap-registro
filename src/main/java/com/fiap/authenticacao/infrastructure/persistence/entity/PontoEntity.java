package com.fiap.authenticacao.infrastructure.persistence.entity;

import com.fiap.authenticacao.domain.model.Ponto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;
import java.util.UUID;

@Entity(name = "ponto")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PontoEntity {
    @Id
    @GeneratedValue
    private UUID id;
    private String usuario;
    private String matricula;
    private String email;
    private final Date data = new Date();

    public Ponto to(PontoEntity ponto) {
        return Ponto.builder()
                .id(ponto.getId())
                .usuario(ponto.getUsuario())
                .matricula(ponto.getMatricula())
                .email(ponto.getEmail())
                .build();
    }
    public PontoEntity from(Ponto ponto) {
        return PontoEntity.builder()
                .id(ponto.getId())
                .usuario(ponto.getUsuario())
                .matricula(ponto.getMatricula())
                .email(ponto.getEmail())
                .build();
    }

}
