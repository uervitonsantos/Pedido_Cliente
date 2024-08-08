package com.cadastro.recepcao.pedido.filterDTO;

import com.cadastro.recepcao.pedido.filtro.ClienteFiltro;
import com.cadastro.recepcao.pedido.filtro.FiltroWrapper;
import com.cadastro.recepcao.pedido.filtro.Paginacao;
import com.cadastro.recepcao.pedido.filtro.PaginacaoFactory;
import com.cadastro.recepcao.pedido.util.constates.Resource;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.ws.rs.QueryParam;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Getter
@Setter
@Schema(name = "Filtro", description = "Filtro para os Dados do Cliente")
@XmlRootElement(name = "Filtro")
@XmlAccessorType(XmlAccessType.FIELD)
public class ClienteFiltroDTO {

    @QueryParam(Resource.P_QUANTIDADE_REGISTROS)
    @Schema(description = "Quantidade de registros", example = "10")
    @XmlElement(nillable = false)
    private Integer quantidadeRegistros;

    @QueryParam(Resource.P_PAGINA)
    @Schema(description = "Pagina", example = "1")
    @XmlElement(nillable = false)
    private Integer pagina;

    @QueryParam(Resource.P_CLIENTE_ID)
    @Schema(description = "Identificador único do cliente", example = "1")
    @XmlElement(nillable = false)
    private List<Long> clienteID;

    @QueryParam(Resource.P_NOME_CLIENTE)
    @Schema(description = "Nome do cliente", example = "Ariano Suassuna")
    @XmlElement(nillable = false)
    private String nomeCliente;

    @QueryParam(Resource.P_PEDIDO_ID)
    @Schema(description = "Identificador único do pedido", example = "1")
    @XmlElement(nillable = false)
    private Long pedidoID;

    @QueryParam(Resource.P_DATA_CADASTRO)
    @Schema(description = "Data do pedido", example = "2024-08-08T13:56:16.920995")
    @XmlElement(nillable = false)
    private LocalDateTime dataCadastroPedido;

    @QueryParam(Resource.P_NOME_PRODUTO)
    @Schema(description = "Nome do produto", example = "produto 3")
    @XmlElement(nillable = false)
    private String nomeProduto;

    @QueryParam(Resource.P_VALOR_PRODUTO)
    @Schema(description = "valor do produto", example = "50.00")
    @XmlElement(nillable = false)
    private Double valorProduto;

    @QueryParam(Resource.P_VALOR_TOTAL)
    @Schema(description = "valor total do pedido", example = "500.00")
    @XmlElement(nillable = false)
    private Double valorTotal;

    @QueryParam(Resource.P_QUATIDADE_PRODUTO)
    @Schema(description = "Quantidade do produto", example = "10")
    @XmlElement(nillable = false)
    private Integer quantidadeProduto;

    public FiltroWrapper filtroWrapper() {
        ClienteFiltro filtro = ClienteFiltro.builder()
                .clienteID(clienteID)
                .nomeCliente(nomeCliente)
                .pedidoID(pedidoID)
                .dataCadastroPedido(dataCadastroPedido)
                .nomeProduto(nomeProduto)
                .valorProduto(valorProduto)
                .valorTotal(valorTotal)
                .quantidadeProduto(quantidadeProduto)
                .build();
        return new FiltroWrapper(filtro, geraPaginacao());

    }

    private Optional<Paginacao> geraPaginacao() {
        return PaginacaoFactory.cria(pagina, quantidadeRegistros);
    }
}
