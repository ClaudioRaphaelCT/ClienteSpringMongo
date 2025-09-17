package com.basic.postgres.client.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Document(collection = "clientes")
public class Cliente {

    @Id
    private String id;
    private LocalDateTime dataInserido;
    private String nome;
    private String sobrenome;
    private Integer idade;
    private String localizacao;
    private Double saldo;
}