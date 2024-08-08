package com.cadastro.recepcao.pedido.entidade;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@Entity
@Table(name = "CLIENTE")
public class Cliente implements Serializable {

    @Id
    @Column(name = "CLIENTE_ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cliente_sequence")
    @SequenceGenerator(name = "cliente_sequence", sequenceName = "SEQ_CLIENTE", allocationSize = 1)
    private Long clienteID;

    @Column(name = "NOME")
    private String nomeCliente;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "cliente", cascade = {CascadeType.PERSIST, CascadeType.REMOVE, CascadeType.MERGE})
    private List<Pedido> pedidos;

}
