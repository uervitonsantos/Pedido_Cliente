package com.cadastro.plano.saude.enumerated;

public enum TipoOrdenacaoEnum {
    NORMAL("NOR"), ID_GROUPBY("IDG");

    private final String codigo;

    private TipoOrdenacaoEnum(String codigo) {
        this.codigo = codigo;
    }

    public String getCodigo() {
        return codigo;
    }
}

