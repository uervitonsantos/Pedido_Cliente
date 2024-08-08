package com.cadastro.recepcao.pedido.filterDTO;

import com.cadastro.recepcao.pedido.filtro.FiltroWrapper;
import com.cadastro.recepcao.pedido.filtro.Paginacao;
import com.cadastro.recepcao.pedido.filtro.PaginacaoFactory;
import com.cadastro.recepcao.pedido.filtro.PedidoFiltro;
import com.cadastro.recepcao.pedido.util.constates.Resource;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.ws.rs.QueryParam;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Optional;

@Getter
@Setter
@Schema(name = "Filtro", description = "Filtro para os Dados do Cliente")
@XmlRootElement(name = "Filtro")
@XmlAccessorType(XmlAccessType.FIELD)
public class PedidoFiltroDTO {

    @QueryParam(Resource.P_QUANTIDADE_REGISTROS)
    @Schema(description = "Quantidade de registros", example = "10")
    @XmlElement(nillable = false)
    private Integer quantidadeRegistros;

    @QueryParam(Resource.P_PAGINA)
    @Schema(description = "Pagina", example = "1")
    @XmlElement(nillable = false)
    private Integer pagina;

    @QueryParam(Resource.P_PEDIDO_ID)
    @Schema(description = "Identificador único do pedido", example = "1")
    @XmlElement(nillable = false)
    private List<Long> pedidoID;

    @QueryParam(Resource.P_NOME_PRODUTO)
    @Schema(description = "Nome do cliente", example = "Ariano Suassuna")
    @XmlElement(nillable = false)
    private String nomeProduto;

    public FiltroWrapper filtroWrapper() {
        PedidoFiltro filtro = PedidoFiltro.builder()
                .pedidoID(pedidoID)
                .nomeProduto(nomeProduto)
                .build();
        return new FiltroWrapper(filtro, geraPaginacao());

    }

    private Optional<Paginacao> geraPaginacao() {
        return PaginacaoFactory.cria(pagina, quantidadeRegistros);
    }
}
