package com.basic.postgres.client.service.command;

import com.basic.postgres.client.dto.api.GlobalResponse;
import com.basic.postgres.client.dto.cliente.ClienteRequest;
import com.basic.postgres.client.dto.cliente.ClienteResponse;
import com.basic.postgres.client.exceptions.NotFoundException;
import com.basic.postgres.client.mapper.ClienteMapper;
import com.basic.postgres.client.repository.ClienteRepository;
import com.basic.postgres.client.utils.MessageService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
@Slf4j
public class ClienteCommandServiceImp implements ClienteCommandService {
    private final ClienteRepository repository;
    private final ClienteMapper mapper;
    private final MessageService message;

    @Override
    @Transactional
    public GlobalResponse<ClienteResponse> inserir(ClienteRequest request) {
        log.info("Inicio: Cliente - inserir");
        var cliente = mapper.toEntity(request);
        log.debug("Request enviado: {}", request);
        cliente.setDataInserido(LocalDateTime.now());
        cliente = repository.save(cliente);
        log.debug("Objeto salvo e retornado do repositório: {}", cliente);
        log.info("Finalizado: Cliente - inserir");
        return new GlobalResponse<>(
                LocalDateTime.now(),
                message.getMessage("cliente.inserir.sucesso", cliente.getNome().toUpperCase()),
                mapper.toDTO(cliente)
        );
    }

    @Override
    @Transactional
    public GlobalResponse<ClienteResponse> atualizar(String id, ClienteRequest request) {
        var cliente = repository.findById(id).orElseThrow(() -> {
            return new NotFoundException(message.getMessage("cliente.id.notfound", id));
        });
        mapper.updateFromRequest(request, cliente);
        var clienteAtualizado = repository.save(cliente);
        return new GlobalResponse<>(
                LocalDateTime.now(),
                message.getMessage("cliente.atualizar.sucesso", id),
                mapper.toDTO(clienteAtualizado)
        );
    }

    @Override
    @Transactional
    public GlobalResponse<ClienteResponse> apagar(String id) {
        long countDeleteId = repository.deleteClienteById(id);
        if (countDeleteId == 0) {
            throw new NotFoundException(message.getMessage("cliente.id.notfound", id));
        }
        return new GlobalResponse<>(
                LocalDateTime.now(),
                message.getMessage("cliente.apagar.sucesso", id),
                null
        );
    }

}
