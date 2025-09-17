package com.basic.postgres.client.mapper;

import com.basic.postgres.client.dto.cliente.ClienteRequest;
import com.basic.postgres.client.dto.cliente.ClienteResponse;
import com.basic.postgres.client.model.Cliente;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ClienteMapper {

    Cliente toEntity(ClienteRequest request);

    ClienteResponse toDTO(Cliente cliente);

    List<ClienteResponse> toDTOList(List<Cliente> responseList);

    void updateFromRequest(ClienteRequest request, @MappingTarget Cliente cliente);
}
