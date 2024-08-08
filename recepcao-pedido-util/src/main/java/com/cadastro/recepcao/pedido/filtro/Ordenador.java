package com.cadastro.recepcao.pedido.filtro;

import java.util.List;

public interface Ordenador {

    boolean hasOrdenacao();

    List<Ordenacao> getOrdenacao();
}
