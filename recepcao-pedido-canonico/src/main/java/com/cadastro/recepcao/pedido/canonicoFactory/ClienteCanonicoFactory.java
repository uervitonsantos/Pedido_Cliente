package com.cadastro.recepcao.pedido.canonicoFactory;

import com.cadastro.recepcao.pedido.canonico.ClienteCanonico;
import com.cadastro.recepcao.pedido.entidade.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class ClienteCanonicoFactory {

    @Autowired
    private PedidoCanonicoFactory pedidoCanonicoFactory;

    public ClienteCanonico builderCliente(Cliente cliente) {
        return Optional.ofNullable(cliente).map(entidade -> {
            return ClienteCanonico.builder()
                    .clienteID(entidade.getClienteID())
                    .nomeCliente(entidade.getNomeCliente())
                    .pedidos(pedidoCanonicoFactory.pedidosCanonico(entidade.getPedidos()))
                    .build();
        }).orElse(null);
    }

    public List<ClienteCanonico> clientesCanonico(List<Cliente> resultList) {
        return Optional.ofNullable(resultList).map(lista -> {
            return lista.stream().map(el -> builderCliente(el)).collect(Collectors.toList());
        }).orElse(new ArrayList<>());

    }
}
