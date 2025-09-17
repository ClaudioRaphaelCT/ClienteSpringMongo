package com.basic.postgres.client.dto.cliente;

import jakarta.validation.constraints.*;

public record ClienteRequest(
        @NotBlank(message = "{cliente.nome.vazio}")
        @Size(min = 3, max = 15, message = "{cliente.nome.tamanho}")
        String nome,
        @NotBlank(message = "{cliente.sobrenome.vazio}")
        @Size(min = 3, max = 15, message = "{cliente.sobrenome.tamanho}")
        String sobrenome,
        @NotNull(message = "")
        @Min(value = 18, message = "{cliente.idade.minima}")
        @Max(value = 99, message = "")
        Integer idade,
        String localizacao,
        @NotNull(message = "")
        @Min(value = 1, message = "")
        Double saldo
) {
}
