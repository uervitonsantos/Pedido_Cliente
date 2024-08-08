package com.cadastro.recepcao.pedido.repository;

import com.cadastro.recepcao.pedido.canonico.ClienteCanonico;
import com.cadastro.recepcao.pedido.canonicoFactory.ClienteCanonicoFactory;
import com.cadastro.recepcao.pedido.entidade.Cliente;
import com.cadastro.recepcao.pedido.entidade.Pedido;
import com.cadastro.recepcao.pedido.filtro.ClienteFiltro;
import com.cadastro.recepcao.pedido.filtro.FiltroWrapper;
import com.google.common.collect.Lists;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ClienteRepository extends RecepcaoPedidosRepositorio {

    @Autowired
    private ClienteCanonicoFactory clienteCanonicoFactory;

    public Cliente busca(Long clienteID) {
        return busca(Cliente.class, clienteID);
    }

    public ClienteCanonico buscaCliente(Long clienteID) {
        Cliente cliente = busca(clienteID);
        return Optional.ofNullable(cliente).map(e -> {
            return clienteCanonicoFactory.builderCliente(e);
        }).orElse(null);
    }

    public List<ClienteCanonico> buscaClientes(FiltroWrapper filtro) {
        ClienteFiltro clienteFiltro = (ClienteFiltro) filtro.getFiltro();
        if (filtro.hasPaginacao()) {
            Long qtdRegistros = countRegistros(clienteFiltro);
            if (qtdRegistros == 0) {
                return Lists.newArrayList();
            }
            filtro.getPaginacao().setTotalRegistros(qtdRegistros);
            CriteriaQuery<Long> criteria = criteriaQuery(Long.class);
            Root<Cliente> root = criteria.from(Cliente.class);
            criteria.distinct(true).select(root.get("clienteID"))
                    .where(aplicaFiltros(root, clienteFiltro, false)).orderBy(asc(root.get("clienteID")));
            List<Long> resultado = paginarResultado(criteria, filtro);
            if (resultado.isEmpty()) {
                return Lists.newArrayList();
            }
            clienteFiltro.setClienteID(resultado);
        }
        return buscaClientes(clienteFiltro);
    }

    private List<ClienteCanonico> buscaClientes(ClienteFiltro clienteFiltro) {
        CriteriaQuery<Cliente> criteria = criteriaQuery(Cliente.class);
        Root<Cliente> root = criteria.from(Cliente.class);
        criteria.distinct(true).select(root).where(aplicaFiltros(root, clienteFiltro, true))
                .orderBy(asc(root.get("clienteID")));
        TypedQuery<Cliente> query = typedQuery(criteria);
        return clienteCanonicoFactory.clientesCanonico(query.getResultList());
    }

    private Long countRegistros(ClienteFiltro clienteFiltro) {
        CriteriaQuery<Long> criteria = criteriaQuery(Long.class);
        Root<Cliente> root = criteria.from(Cliente.class);
        criteria.select(criteriaBuilder().countDistinct(root.get("clienteID")))
                .where(aplicaFiltros(root, clienteFiltro, false));
        return typedQuery(criteria).getSingleResult();
    }

    private Predicate[] aplicaFiltros(Root<Cliente> root, ClienteFiltro clienteFiltro, boolean fetch) {
        CriteriaBuilder builder = criteriaBuilder();
        List<Predicate> predicates = Lists.newArrayList();

        Join<Cliente, Pedido> joinPedidos = root.join("pedidos", JoinType.LEFT);

        if (clienteFiltro.hasNomeCliente()) {
            predicates.add(builder.like(builder.upper(root.get("nomeCliente")),
                    "%" + clienteFiltro.getNomeCliente().toUpperCase() + "%"));
        }
        if (clienteFiltro.hasPedidoID()) {
            predicates.add(builder.equal(joinPedidos.get("pedidoID"), clienteFiltro.getPedidoID()));
        }
        if (clienteFiltro.hasDataCadastroPedido()) {
            predicates.add(builder.equal(joinPedidos.get("dataCadastroPedido"), clienteFiltro.getDataCadastroPedido()));
        }
        if (clienteFiltro.hasNomeProduto()) {
            predicates.add(builder.like(builder.upper(joinPedidos.get("nomeProduto")),
                    "%" + clienteFiltro.getNomeProduto().toUpperCase() + "%"));
        }
        if (clienteFiltro.hasValorProduto()) {
            predicates.add(builder.equal(joinPedidos.get("valorProduto"), clienteFiltro.getValorProduto()));
        }
        if (clienteFiltro.hasValorTotal()) {
            predicates.add(builder.equal(joinPedidos.get("valorTotal"), clienteFiltro.getValorTotal()));
        }
        if (clienteFiltro.hasQuantidadeProduto()) {
            predicates.add(builder.equal(joinPedidos.get("quantidadeProduto"), clienteFiltro.getQuantidadeProduto()));
        }

        return predicates.stream().toArray(Predicate[]::new);
    }

    public Cliente salvaCliente(Cliente cliente) {
        return merge(cliente);
    }
}
