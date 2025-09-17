package com.basic.postgres.client.controller.command;

import com.basic.postgres.client.dto.api.GlobalResponse;
import com.basic.postgres.client.dto.cliente.ClienteRequest;
import com.basic.postgres.client.dto.cliente.ClienteResponse;
import com.basic.postgres.client.service.command.ClienteCommandService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("v1/clientes")
@Tag(name = "Gerenciamento de Clientes", description = "Endpoints para a criação, atualização e exclusão de clientes.")
@AllArgsConstructor
public class ClienteCommandController {
    private final ClienteCommandService service;

    @PostMapping
    public ResponseEntity<GlobalResponse<ClienteResponse>> inserir(@Valid @RequestBody ClienteRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.inserir(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<GlobalResponse<ClienteResponse>> atualizar(
            @PathVariable("id") String id, @Valid @RequestBody ClienteRequest request) {
        return ResponseEntity.ok().body(service.atualizar(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<GlobalResponse<ClienteResponse>> apagar(@PathVariable("id") String id) {
        return ResponseEntity.ok().body(service.apagar(id));
    }
}
