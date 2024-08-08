package com.cadastro.recepcao.pedido.dto;


import com.cadastro.recepcao.pedido.util.adapter.LocalDateTimeAdapter;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(name = "Pedido", description = "Dados do Pedido")
@XmlRootElement(name = "Pedido")
@XmlAccessorType(XmlAccessType.FIELD)
public class PedidoDTO {

    @Schema(description = "Identificador único do pedido", example = "1")
    @XmlElement(nillable = false)
    private Long pedidoID;

    @Schema(description = "Data de cadastro do pedido", example = "08-008-2024")
    @XmlElement(nillable = false)
    @XmlJavaTypeAdapter(LocalDateTimeAdapter.class)
    private LocalDateTime dataCadastroPedido;

    @Schema(description = "Nome do produto", example = "Nome...")
    @XmlElement(nillable = false)
    private String nomeProduto;

    @Schema(description = "Identificador único do cliente", example = "1")
    @XmlElement(nillable = false)
    private Long clienteID;

    @Schema(description = "Valor do produto", example = "50.00")
    @XmlElement(nillable = false)
    private Double valorProduto;

    @Schema(description = "Valor do total", example = "350.00")
    @XmlElement(nillable = false)
    private Double valorTotal;

    @Schema(description = "Quantidade do produto", example = "3")
    @XmlElement(nillable = false)
    private Integer quantidadeProduto;

}
