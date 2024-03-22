package com.fiap.registro.domain.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class Ponto {
    private UUID id;
    private String usuario;
    private String matricula;
    private String email;
    @JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss")
    private final Date data = new Date();

    public String toJsonStr() {
        StringBuilder sb = new StringBuilder();
        sb.append("{'id': '"+this.id+"', ");
        sb.append("'usuario': '"+this.usuario+"', ");
        sb.append("'matricula': '"+this.matricula+"', ");
        sb.append("'email': '"+this.email+"', ");
        sb.append("'data': '"+new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").format(this.data)+"'} ");
        return sb.toString();
    }
}
