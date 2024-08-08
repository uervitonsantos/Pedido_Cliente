package com.cadastro.recepcao.pedido.canonicoFactory;

import com.cadastro.recepcao.pedido.canonico.PedidoCanonico;
import com.cadastro.recepcao.pedido.entidade.Pedido;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class PedidoCanonicoFactory {

    public PedidoCanonico builderPedido(Pedido pedido) {
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

    public List<PedidoCanonico> pedidosCanonico(List<Pedido> resultList) {
        return Optional.ofNullable(resultList).map(lista -> {
            return lista.stream().map(el -> builderPedido(el)).collect(Collectors.toList());
        }).orElse(new ArrayList<>());

    }
}
