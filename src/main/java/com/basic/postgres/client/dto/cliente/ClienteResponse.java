package com.basic.postgres.client.dto.cliente;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;

public record ClienteResponse(
        String id,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss")
        @JsonProperty("data_inserido")
        LocalDateTime dataInserido,
        String nome,
        String sobrenome,
        Integer idade,
        String localizacao,
        Double saldo
) {
}
