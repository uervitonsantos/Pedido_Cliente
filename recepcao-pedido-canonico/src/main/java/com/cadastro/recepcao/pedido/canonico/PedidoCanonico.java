package com.cadastro.recepcao.pedido.canonico;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PedidoCanonico {

    private Long pedidoID;
    private LocalDateTime dataCadastroPedido;
    private String nomeProduto;
    private Long clienteID;
    private Double valorProduto;
    private Double valorTotal;
    private Integer quantidadeProduto;
}
