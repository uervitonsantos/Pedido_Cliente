package com.cadastro.recepcao.pedido.filtro;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ClienteFiltro implements Filtro{

    private List<Long> clienteID;
    private String nomeCliente;

    private Long pedidoID;
    private LocalDateTime dataCadastroPedido;
    private String nomeProduto;
    private Double valorProduto;
    private Double valorTotal;
    private Integer quantidadeProduto;

    public Boolean hasClienteID() {
        return clienteID != null;
    }
    public Boolean hasNomeCliente() {
        return nomeCliente != null;
    }
    public Boolean hasPedidoID() {
        return pedidoID != null;
    }
    public Boolean hasDataCadastroPedido() {
        return dataCadastroPedido != null;
    }
    public Boolean hasNomeProduto() {
        return nomeProduto != null;
    }
    public Boolean hasValorProduto() {
        return valorProduto != null;
    }
    public Boolean hasValorTotal() {
        return valorTotal != null;
    }
    public Boolean hasQuantidadeProduto() {
        return quantidadeProduto != null;
    }
}
