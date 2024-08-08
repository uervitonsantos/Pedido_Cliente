package com.cadastro.recepcao.pedido.entidade;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@Entity
@Table(name = "PEDIDO")
@NamedQuery(name = "Pedido.buscaPorNomeProduto", query = "SELECT p FROM Pedido p WHERE p.nomeProduto = :pnomeProduto")
public class Pedido implements Serializable {

    @Id
    @Column(name = "PEDIDO_ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pedido_sequence")
    @SequenceGenerator(name = "pedido_sequence", sequenceName = "SEQ_PEDIDO", allocationSize = 1)
    private Long pedidoID;

    @Column(name = "DATA_CADASTRO_PEDIDO")
    private LocalDateTime dataCadastroPedido;

    @Column(name = "NOME_PRODUTO")
    private String nomeProduto;

    @Column(name = "CLIENTE_ID")
    private Long clienteID;

    @Column(name = "VALOR_PRODUTO")
    private Double valorProduto;

    @Column(name = "VALOR_TOTAL")
    private Double valorTotal;

    @Column(name = "QUANTIDADE_PRODUTO")
    private Integer quantidadeProduto;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CLIENTE_ID", referencedColumnName = "CLIENTE_ID", insertable = false, updatable = false)
    private Cliente cliente;

}
