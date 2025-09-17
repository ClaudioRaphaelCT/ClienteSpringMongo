package com.basic.postgres.client.dto.api;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public record GlobalResponse<T>(
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss")
        LocalDateTime timestamp,
        String mensagem,
        T dados
) {
}
