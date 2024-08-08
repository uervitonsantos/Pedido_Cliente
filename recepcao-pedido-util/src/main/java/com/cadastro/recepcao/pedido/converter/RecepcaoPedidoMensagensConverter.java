package com.cadastro.recepcao.pedido.converter;


import com.cadastro.recepcao.pedido.util.RecepcaoPedidoMensagens;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.Optional;

@Converter(autoApply = true)
public class RecepcaoPedidoMensagensConverter implements AttributeConverter<RecepcaoPedidoMensagens, String> {
    @Override
    public String convertToDatabaseColumn(RecepcaoPedidoMensagens recepcaoPedidoMensagens) {
        return Optional.ofNullable(recepcaoPedidoMensagens).map(a -> recepcaoPedidoMensagens.getValor()).orElse(null);
    }

    @Override
    public RecepcaoPedidoMensagens convertToEntityAttribute(String string) {
        return RecepcaoPedidoMensagens.findByCodigo(string);
    }
}
