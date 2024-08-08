package com.cadastro.recepcao.pedido.filtro;

public enum CampoOrdenacao {

    //formatter:off
    PEDIDO_ID("pedidoID"),
    ;
    private String valor;

    private CampoOrdenacao(String valor) {
        this.valor = valor;
    }

    public String getValor() {
        return valor;
    }
}
