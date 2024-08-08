package com.cadastro.recepcao.pedido.filtro;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PedidoFiltro implements Filtro{

    private List<Long> pedidoID;
    private String nomeProduto;
}
