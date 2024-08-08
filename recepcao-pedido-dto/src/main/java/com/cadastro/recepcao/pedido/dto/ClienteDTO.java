package com.cadastro.recepcao.pedido.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(name = "Cliente", description = "Dados do Cliente")
@XmlRootElement(name = "Cliente")
@XmlAccessorType(XmlAccessType.FIELD)
public class ClienteDTO {

    @Schema(description = "Identificador único do cliente", example = "1")
    @XmlElement(nillable = false)
    private Long clienteID;

    @Schema(description = "Nome do cliente", example = "Ariano Suassuna")
    @XmlElement(nillable = false)
    private String nomeCliente;

    @XmlElement(nillable = false)
    private List<PedidoDTO> pedidos;
}
