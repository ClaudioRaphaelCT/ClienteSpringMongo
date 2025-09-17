package com.basic.postgres.client.service.command;

import com.basic.postgres.client.dto.api.GlobalResponse;
import com.basic.postgres.client.dto.cliente.ClienteRequest;
import com.basic.postgres.client.dto.cliente.ClienteResponse;

public interface ClienteCommandService {
    GlobalResponse<ClienteResponse> inserir(ClienteRequest request);

    GlobalResponse<ClienteResponse> atualizar(String id, ClienteRequest request);

    GlobalResponse<ClienteResponse> apagar(String id);
}
