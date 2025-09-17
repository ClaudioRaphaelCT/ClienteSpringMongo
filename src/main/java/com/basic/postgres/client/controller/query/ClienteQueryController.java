package com.basic.postgres.client.controller.query;

import com.basic.postgres.client.dto.api.GlobalResponse;
import com.basic.postgres.client.dto.cliente.ClienteResponse;
import com.basic.postgres.client.service.query.ClienteQueryService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/clientes")
@Tag(name = "Visualização de Clientes", description = "Endpoints para a visualização de clientes.")
@AllArgsConstructor
public class ClienteQueryController {
    private final ClienteQueryService service;

    @GetMapping
    public ResponseEntity<GlobalResponse<List<ClienteResponse>>> visualizarTodos() {
        return ResponseEntity.ok().body(service.visualizarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<GlobalResponse<ClienteResponse>> visualizarPorId(@PathVariable("id") String id) {
        return ResponseEntity.ok().body(service.visualizarPorId(id));
    }

    @GetMapping(params = "nome")
    public ResponseEntity<GlobalResponse<ClienteResponse>> visualizarPorNome(@RequestParam("nome") String nome) {
        return ResponseEntity.ok().body(service.visualizarPorNome(nome));
    }
}
