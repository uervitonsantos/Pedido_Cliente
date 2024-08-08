package com.cadastro.recepcao.pedido.dtoFactory;

import com.cadastro.recepcao.pedido.canonico.PedidoCanonico;
import com.cadastro.recepcao.pedido.canonicoFactory.ClienteCanonicoFactory;
import com.cadastro.recepcao.pedido.dto.PedidoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class PedidoDTOFactory {

    public PedidoDTO builderPedidoDto(PedidoCanonico pedido) {
        return Optional.ofNullable(pedido).map(entidade -> {
            return PedidoDTO.builder()
                    .pedidoID(entidade.getPedidoID())
                    .dataCadastroPedido(entidade.getDataCadastroPedido())
                    .nomeProduto(entidade.getNomeProduto())
                    .clienteID(entidade.getClienteID())
                    .valorProduto(entidade.getValorProduto())
                    .valorTotal(entidade.getValorTotal())
                    .quantidadeProduto(entidade.getQuantidadeProduto())
                    .build();
        }).orElse(null);
    }

    public List<PedidoDTO> pedidosDto(List<PedidoCanonico> resultList) {
        return Optional.ofNullable(resultList).map(lista -> {
            return lista.stream().map(el -> builderPedidoDto(el)).collect(Collectors.toList());
        }).orElse(new ArrayList<>());

    }

    public PedidoCanonico builderPedidoCanonico(PedidoDTO pedido) {
        return Optional.ofNullable(pedido).map(entidade -> {
            return PedidoCanonico.builder()
                    .pedidoID(entidade.getPedidoID())
                    .dataCadastroPedido(entidade.getDataCadastroPedido())
                    .nomeProduto(entidade.getNomeProduto())
                    .clienteID(entidade.getClienteID())
                    .valorProduto(entidade.getValorProduto())
                    .valorTotal(entidade.getValorTotal())
                    .quantidadeProduto(entidade.getQuantidadeProduto())
                    .build();
        }).orElse(null);
    }

    public List<PedidoCanonico> pedidosCanonico(List<PedidoDTO> resultList) {
        return Optional.ofNullable(resultList).map(lista -> {
            return lista.stream().map(el -> builderPedidoCanonico(el)).collect(Collectors.toList());
        }).orElse(new ArrayList<>());

    }
}
