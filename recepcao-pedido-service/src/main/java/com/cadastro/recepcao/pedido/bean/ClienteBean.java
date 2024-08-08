package com.cadastro.recepcao.pedido.bean;

import com.cadastro.recepcao.pedido.canonico.ClienteCanonico;
import com.cadastro.recepcao.pedido.filtro.FiltroWrapper;
import com.cadastro.recepcao.pedido.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ClienteBean {

    @Autowired
    private ClienteService service;

    public ClienteCanonico buscaCliente(Long clienteID) {
        return service.buscaCliente(clienteID);
    }

    public List<ClienteCanonico> buscaClientes(FiltroWrapper filtro) {
        return service.buscaClientes(filtro);
    }

    public ClienteCanonico criaCliente(ClienteCanonico canonico) {
        return service.criaCliente(canonico);
    }

    public ClienteCanonico editaCliente(ClienteCanonico canonico) {
        Long editaCliente = service.editaCliente(canonico);
        return buscaCliente(editaCliente);
    }

    public void removeCliente(Long clienteID) {
        service.removeCliente(clienteID);
    }
}
