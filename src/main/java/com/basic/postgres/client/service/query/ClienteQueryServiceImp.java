package com.basic.postgres.client.service.query;

import com.basic.postgres.client.dto.api.GlobalResponse;
import com.basic.postgres.client.dto.cliente.ClienteResponse;
import com.basic.postgres.client.exceptions.NotFoundException;
import com.basic.postgres.client.mapper.ClienteMapper;
import com.basic.postgres.client.model.Cliente;
import com.basic.postgres.client.repository.ClienteRepository;
import com.basic.postgres.client.utils.MessageService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class ClienteQueryServiceImp implements ClienteQueryService {
    private final ClienteRepository repository;
    private final ClienteMapper mapper;
    private final MessageService message;

    public GlobalResponse<List<ClienteResponse>> visualizarTodos() {
        List<Cliente> clientes = repository.findAll();
        return new GlobalResponse<>(
                LocalDateTime.now(),
                message.getMessage("cliente.visualizar.todos"),
                mapper.toDTOList(clientes)
        );
    }

    public GlobalResponse<ClienteResponse> visualizarPorId(String id) {
        var cliente = repository.findById(id).orElseThrow(() -> {
            return new NotFoundException(message.getMessage("cliente.id.notfound", id));
        });
        return new GlobalResponse<>(
                LocalDateTime.now(),
                message.getMessage("cliente.visualizar.id", id),
                mapper.toDTO(cliente)
        );
    }

    public GlobalResponse<ClienteResponse> visualizarPorNome(String nome) {
        var cliente = repository.findByNome(nome);
        if (cliente == null) {
            throw new NotFoundException(message.getMessage("cliente.nome.notfound", nome));
        }
        return new GlobalResponse<>(
                LocalDateTime.now(),
                message.getMessage("cliente.visualizar.nome", nome.toUpperCase()),
                mapper.toDTO(cliente)
        );
    }

}
