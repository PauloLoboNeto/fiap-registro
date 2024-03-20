package com.fiap.authenticacao.application.web.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StandardError {
    private Long timeStamp;
    private Integer status;
    private String error;
    private String message;
    private String path;
}
