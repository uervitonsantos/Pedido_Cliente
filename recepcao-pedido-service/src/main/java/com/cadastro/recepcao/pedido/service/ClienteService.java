package com.cadastro.recepcao.pedido.service;

import com.cadastro.recepcao.pedido.canonico.ClienteCanonico;
import com.cadastro.recepcao.pedido.entidade.Cliente;
import com.cadastro.recepcao.pedido.filtro.FiltroWrapper;
import com.cadastro.recepcao.pedido.repository.ClienteRepository;
import com.cadastro.recepcao.pedido.util.RecepcaoPedidoMensagens;
import com.cadastro.recepcao.pedido.util.exception.ValidacaoException;
import com.google.common.base.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public ClienteCanonico buscaCliente(Long clienteID) {
        return Optional.ofNullable(clienteRepository.buscaCliente(clienteID)).orElseThrow(
                () -> new ValidacaoException(RecepcaoPedidoMensagens.ERRO_VALIDACAO_COD_CLIENTE_NAO_EXISTE.getValor()));
    }

    public Cliente buscarCliente(Long clienteID) {
        return Optional.ofNullable(clienteRepository.busca(clienteID)).orElseThrow(
                () -> new ValidacaoException(RecepcaoPedidoMensagens.ERRO_VALIDACAO_COD_CLIENTE_NAO_EXISTE.getValor()));
    }

    public List<ClienteCanonico> buscaClientes(FiltroWrapper filtro) {
        return clienteRepository.buscaClientes(filtro);
    }

    public ClienteCanonico criaCliente(ClienteCanonico canonico) {
        validaIDCliente(canonico);
        validaDadosCliente(canonico);
        Long cliente = salvaCliente(canonico);
        return buscaCliente(cliente);
    }

    private void validaIDCliente(ClienteCanonico canonico) {
        ClienteCanonico cliente = clienteRepository.buscaCliente(canonico.getClienteID());
        if (cliente != null) {
            throw new ValidacaoException(RecepcaoPedidoMensagens.ERRO_VALIDACAO_COD_CLIENTE_JA_EXISTE.getValor());
        }
    }

    public Long editaCliente(ClienteCanonico canonico) {
        Cliente cliente = buscarCliente(canonico.getClienteID());
        validaDadosCliente(canonico);
        popularCliente(cliente, canonico);
        cliente = clienteRepository.merge(cliente);
        return cliente.getClienteID();
    }

    private void popularCliente(Cliente cliente, ClienteCanonico canonico) {
        cliente.setNomeCliente(canonico.getNomeCliente());
    }

    private void validaDadosCliente(ClienteCanonico canonico) {
        if (Strings.isNullOrEmpty(canonico.getNomeCliente())) {
            throw new ValidacaoException(RecepcaoPedidoMensagens.ERRO_VALIDACAO_NOME_CLIENTE_OBRIGATORIO.getValor());
        }
    }

    private Long salvaCliente(ClienteCanonico canonico) {
        Cliente cliente = geraBeneficario(canonico);
        cliente.setNomeCliente(canonico.getNomeCliente());
        Cliente clienteSalvo = clienteRepository.salvaCliente(cliente);
        return clienteSalvo.getClienteID();
    }

    private Cliente geraBeneficario(ClienteCanonico canonico) {
        if (canonico.getClienteID() == null) {
            return new Cliente();
        }
        return clienteRepository.busca(canonico.getClienteID());
    }

    public void removeCliente(Long clienteID) {
        Cliente cliente = buscarCliente(clienteID);
        clienteRepository.remove(cliente);
    }
}
