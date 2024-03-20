package com.fiap.authenticacao.infrastructure;

import org.springframework.security.crypto.bcrypt.BCrypt;

import java.util.Objects;

public abstract class Encrypt {
    private final static int WORKLOAD = 12;

    public static String gerarHash(String texto) {
        String salt = BCrypt.gensalt(WORKLOAD);
        return BCrypt.hashpw(texto, salt);
    }

    public static boolean verificaHash(String texto, String textoVerificar) {
        boolean verificado = false;
        if (Objects.isNull(textoVerificar) || !textoVerificar.startsWith("$2a$"))
            throw new java.lang.IllegalArgumentException("Hash inválido fornecido para comparação");

        return BCrypt.checkpw(texto, textoVerificar);
    }
}
