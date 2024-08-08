package com.cadastro.recepcao.pedido.dtoFactory;

import com.cadastro.recepcao.pedido.canonico.ClienteCanonico;
import com.cadastro.recepcao.pedido.dto.ClienteDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class ClienteDTOFactory {

    @Autowired
    private PedidoDTOFactory pedidoDTOFactory;

    public ClienteDTO builderClienteDto(ClienteCanonico cliente) {
        return Optional.ofNullable(cliente).map(entidade -> {
            return ClienteDTO.builder()
                    .clienteID(entidade.getClienteID())
                    .nomeCliente(entidade.getNomeCliente())
                    .pedidos(pedidoDTOFactory.pedidosDto(entidade.getPedidos()))
                    .build();
        }).orElse(null);
    }

    public List<ClienteDTO> clientesDto(List<ClienteCanonico> resultList) {
        return Optional.ofNullable(resultList).map(lista -> {
            return lista.stream().map(el -> builderClienteDto(el)).collect(Collectors.toList());
        }).orElse(new ArrayList<ClienteDTO>());

    }

    public ClienteCanonico builderClienteCanonico(ClienteDTO cliente) {
        return Optional.ofNullable(cliente).map(entidade -> {
            return ClienteCanonico.builder()
                    .clienteID(entidade.getClienteID())
                    .nomeCliente(entidade.getNomeCliente())
                    .build();
        }).orElse(null);
    }

    public List<ClienteCanonico> clientesCanonico(List<ClienteDTO> resultList) {
        return Optional.ofNullable(resultList).map(lista -> {
            return lista.stream().map(el -> builderClienteCanonico(el)).collect(Collectors.toList());
        }).orElse(new ArrayList<ClienteCanonico>());

    }
}
