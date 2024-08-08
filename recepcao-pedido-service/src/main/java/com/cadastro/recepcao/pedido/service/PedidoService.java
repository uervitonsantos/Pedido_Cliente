package com.cadastro.recepcao.pedido.service;

import com.cadastro.recepcao.pedido.canonico.ClienteCanonico;
import com.cadastro.recepcao.pedido.canonico.PedidoCanonico;
import com.cadastro.recepcao.pedido.entidade.Cliente;
import com.cadastro.recepcao.pedido.entidade.Pedido;
import com.cadastro.recepcao.pedido.filtro.FiltroWrapper;
import com.cadastro.recepcao.pedido.repository.PedidoRepository;
import com.cadastro.recepcao.pedido.util.RecepcaoPedidoMensagens;
import com.cadastro.recepcao.pedido.util.exception.ValidacaoException;
import com.google.common.base.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class PedidoService {

    LocalDateTime dataAtual = LocalDateTime.now();

    @Autowired
    private PedidoRepository pedidoRepository;


    public PedidoCanonico buscaPedido(Long pedidoID) {
        return Optional.ofNullable(pedidoRepository.buscaPedido(pedidoID))
                .orElseThrow(() -> new ValidacaoException(RecepcaoPedidoMensagens.ERRO_VALIDACAO_COD_PEDIDO_NAO_EXISTE.getValor()));
    }

    public Pedido buscarPedido(Long pedidoID) {
        return Optional.ofNullable(pedidoRepository.busca(Pedido.class, pedidoID))
                .orElseThrow(() -> new ValidacaoException(RecepcaoPedidoMensagens.ERRO_VALIDACAO_COD_PEDIDO_NAO_EXISTE.getValor()));
    }

    public List<PedidoCanonico> buscaPedidos(FiltroWrapper filtro) {
        return pedidoRepository.buscaPedidos(filtro);
    }

    public PedidoCanonico criaPedido(PedidoCanonico canonico) {
        validaIDPedido(canonico);
        validaDadosPedido(canonico);
        Long cliente = salvaPedido(canonico);
        return buscaPedido(cliente);
    }

    public Long editaPedido(PedidoCanonico canonico) {
        Pedido pedido = buscarPedido(canonico.getPedidoID());
        validaIDPedido(canonico);
        popularPedido(pedido, canonico);
        pedido = pedidoRepository.merge(pedido);
        return pedido.getPedidoID();
    }

    private void popularPedido(Pedido pedido, PedidoCanonico canonico) {
        pedido.setDataCadastroPedido(dataAtual);
        pedido.setNomeProduto(canonico.getNomeProduto());
        pedido.setValorProduto(canonico.getValorProduto());
        pedido.setQuantidadeProduto(canonico.getQuantidadeProduto());
    }

    private void validaIDPedido(PedidoCanonico canonico) {
        PedidoCanonico pedido = pedidoRepository.buscaPedido(canonico.getPedidoID());
        if (pedido != null) {
            throw new ValidacaoException(RecepcaoPedidoMensagens.ERRO_VALIDACAO_COD_PEDIDO_JA_EXISTE.getValor());
        }
    }

    public void validaDadosPedido(PedidoCanonico pedidoCanonico) {
        if (pedidoCanonico == null) {
            throw new ValidacaoException(RecepcaoPedidoMensagens.ERRO_VALIDACAO_PEDIDO_OBRIGATORIO.getValor());
        } else {
            if (Strings.isNullOrEmpty(pedidoCanonico.getNomeProduto())) {
                throw new ValidacaoException(RecepcaoPedidoMensagens.ERRO_VALIDACAO_NOME_PRODUTO_OBRIGATORIO.getValor());
            }
            if (pedidoCanonico.getValorProduto() == null) {
                throw new ValidacaoException(RecepcaoPedidoMensagens.ERRO_VALIDACAO_VALOR_PRODUTO_OBRIGATORIO.getValor());
            }

        }
    }

    private Long salvaPedido(PedidoCanonico canonico) {
        Double valorProduto = canonico.getValorProduto();
        Double valorTotal;
        Pedido pedido = geraPedido(canonico);
        pedido.setDataCadastroPedido(dataAtual);
        pedido.setNomeProduto(canonico.getNomeProduto());
        Integer quantidade = canonico.getQuantidadeProduto();
        if(quantidade == null){
            pedido.setQuantidadeProduto(1);
        }
        if(quantidade > 5){
            if (quantidade >= 10) {
                valorTotal = valorProduto * quantidade * 0.9;
            } else {
                valorTotal = valorProduto * quantidade * 0.95;
            }
        } else {
            valorTotal = valorProduto * quantidade;
        }
        pedido.setQuantidadeProduto(quantidade);
        pedido.setValorProduto(valorProduto);
        pedido.setValorTotal(valorTotal);
        pedido.setClienteID(canonico.getClienteID());
        Pedido pedidoSalvo = pedidoRepository.salvaPedido(pedido);
        return pedidoSalvo.getPedidoID();
    }

    private Pedido geraPedido(PedidoCanonico canonico) {
        if (canonico.getPedidoID() == null) {
            return new Pedido();
        }
        return pedidoRepository.busca(canonico.getPedidoID());
    }

    public void removePedido(Long pedidoID) {
        Pedido pedido = buscarPedido(pedidoID);
        pedidoRepository.remove(pedido);
    }

}
