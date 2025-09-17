package com.basic.postgres.client.repository;

import com.basic.postgres.client.model.Cliente;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ClienteRepository extends MongoRepository<Cliente, String> {
    long deleteClienteById(String id);

    Cliente findByNome(String nome);
}
