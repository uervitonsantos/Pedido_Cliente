package com.cadastro.recepcao.pedido.util.constates;

import com.cadastro.recepcao.pedido.filtro.FiltroWrapper;
import com.cadastro.recepcao.pedido.filtro.Paginacao;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.ResponseBuilder;
import jakarta.ws.rs.core.Response.Status;

import java.util.Optional;

import static jakarta.ws.rs.core.HttpHeaders.CONTENT_DISPOSITION;

public interface Resource {
    public static final String RESPONSE_OK = "200";
    public static final String RESPONSE_BAD_REQUEST = "400";
    public static final String RESPONSE_UNAUTHORIZED = "401";
    public static final String RESPONSE_FORBIDDEN = "403";
    public static final String RESPONSE_NO_CONTENT = "204";
    public static final String RESPONSE_CREATED = "201";
    public static final String RESPONSE_PARCIAL = "206";
    public static final String P_QUANTIDADE_TOTAL = "quantidadeTotal";
    public static final String ATTACHMENT = "attachment; filename=%s";
    public static final String P_QUANTIDADE_REGISTROS = "quantidadeRegistros";
    public static final String P_PAGINA = "pagina";
    public static final String P_CLIENTE_ID = "clienteID";
    public static final String P_NOME_CLIENTE = "nomeCliente";
    public static final String P_PEDIDO_ID = "pedidoID";
    public static final String P_DATA_CADASTRO = "dataCadastroPedido";
    public static final String P_NOME_PRODUTO = "nomeProduto";
    public static final String P_VALOR_PRODUTO = "valorProduto";
    public static final String P_VALOR_TOTAL = "valorTotal";
    public static final String P_QUATIDADE_PRODUTO = "quantidadeProduto";

    default ResponseBuilder respostaPaginada(FiltroWrapper wrapper) {
        return respostaPaginada(wrapper.getPaginacaoOptional());
    }

    default ResponseBuilder respostaPaginada(Optional<Paginacao> paginacao) {
        ResponseBuilder builder = Response.ok();
        if (paginacao.isPresent()) {
            if (paginacao.get().hasMais()) {
                builder.status(Status.PARTIAL_CONTENT);
            }
            builder.header(P_QUANTIDADE_TOTAL, paginacao.get().getTotalRegistros());
        }
        return builder;
    }

    default ResponseBuilder respostaArquivo(byte[] conteudo, String nomeArquivo) {
        return Response.ok(conteudo).header(CONTENT_DISPOSITION, String.format(ATTACHMENT, nomeArquivo));
    }
}
