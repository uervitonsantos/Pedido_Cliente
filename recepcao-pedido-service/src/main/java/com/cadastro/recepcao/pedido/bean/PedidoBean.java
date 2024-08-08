package com.cadastro.recepcao.pedido.bean;

import com.cadastro.recepcao.pedido.canonico.PedidoCanonico;
import com.cadastro.recepcao.pedido.filtro.FiltroWrapper;
import com.cadastro.recepcao.pedido.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PedidoBean {

    @Autowired
    private PedidoService service;

    public PedidoCanonico buscaPedido(Long pedidoID) {
        return service.buscaPedido(pedidoID);
    }

    public List<PedidoCanonico> buscaPedidos(FiltroWrapper filtro) {
        return service.buscaPedidos(filtro);
    }

    public PedidoCanonico criaPedido(PedidoCanonico canonico) {
        return service.criaPedido(canonico);
    }

    public PedidoCanonico editaPedido(PedidoCanonico canonico) {
        Long editaPedido = service.editaPedido(canonico);
        return buscaPedido(editaPedido);
    }

    public void removePedido(Long pedidoID) {
        service.removePedido(pedidoID);
    }
}
