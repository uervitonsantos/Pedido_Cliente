package com.cadastro.recepcao.pedido.canonico;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClienteCanonico {

    private Long clienteID;
    private String nomeCliente;
    private List<PedidoCanonico> pedidos;

}
