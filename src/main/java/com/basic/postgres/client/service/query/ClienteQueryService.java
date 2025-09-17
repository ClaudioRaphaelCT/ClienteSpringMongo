package com.basic.postgres.client.service.query;

import com.basic.postgres.client.dto.api.GlobalResponse;
import com.basic.postgres.client.dto.cliente.ClienteResponse;

import java.util.List;

public interface ClienteQueryService {
    GlobalResponse<List<ClienteResponse>> visualizarTodos();

    GlobalResponse<ClienteResponse> visualizarPorId(String id);

    GlobalResponse<ClienteResponse> visualizarPorNome(String nome);
}
