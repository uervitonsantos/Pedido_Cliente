package com.cadastro.recepcao.pedido.util;

public enum RecepcaoPedidoMensagens {

    ERRO_VALIDACAO_COD_PEDIDO_NAO_EXISTE("ID do pedido não existe"),
    ERRO_VALIDACAO_PEDIDO_OBRIGATORIO("Obrigatório informar o pedido"),
    ERRO_VALIDACAO_NOME_PRODUTO_OBRIGATORIO("Obrigatório informar o nome do produto"),
    ERRO_VALIDACAO_VALOR_PRODUTO_OBRIGATORIO("Obrigatório informar o valor do produto"),
    ERRO_VALIDACAO_QUANTIDADE_PRODUTO_OBRIGATORIO("Obrigatório informar a quantidade do produto"),
    ERRO_VALIDACAO_NOME_PRODUTO_JA_EXISTE("Nome do produdo já cadastrado"),
    ERRO_VALIDACAO_NOME_CLIENTE_OBRIGATORIO("Obrigatório informar o nome do cliente"),
    ERRO_VALIDACAO_COD_CLIENTE_NAO_EXISTE("ID do cliente não existe"),
    ERRO_VALIDACAO_COD_CLIENTE_JA_EXISTE("ID do cliente já existe"),
    ERRO_VALIDACAO_COD_PEDIDO_JA_EXISTE("ID do pedido já existe");


    private final String valor;

    private RecepcaoPedidoMensagens(String valor) {
        this.valor = valor;
    }

    public static RecepcaoPedidoMensagens findByCodigo(String valor) {
        for (RecepcaoPedidoMensagens value : RecepcaoPedidoMensagens.values()) {
            if (value.getValor().equalsIgnoreCase(valor.trim())) {
                return value;
            }
        }
        return null;
    }

    public String getValor() {
        return valor;
    }

}
