package com.fiap.registro.domain.ports.out;

public interface IAtualizaRegistroPontoQueuePort {
    void publish(String mensagem);
}
