package com.cadastro.recepcao.pedido.repository;

import com.cadastro.recepcao.pedido.canonico.PedidoCanonico;
import com.cadastro.recepcao.pedido.canonicoFactory.PedidoCanonicoFactory;
import com.cadastro.recepcao.pedido.entidade.Cliente;
import com.cadastro.recepcao.pedido.entidade.Pedido;
import com.cadastro.recepcao.pedido.filtro.FiltroWrapper;
import com.cadastro.recepcao.pedido.filtro.PedidoFiltro;
import com.google.common.collect.Lists;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class PedidoRepository extends RecepcaoPedidosRepositorio {

    @Autowired
    private PedidoCanonicoFactory pedidoCanonicoFactory;

    public Pedido busca(Long pedidoID) {
        return busca(Pedido.class, pedidoID);
    }

    public PedidoCanonico buscaPedido(Long pedidoID) {
        Pedido pedido = busca(pedidoID);
        return Optional.ofNullable(pedido).map(e -> {
            return pedidoCanonicoFactory.builderPedido(e);
        }).orElse(null);
    }
    public Pedido buscarPedidoPorNomeProduto(List<PedidoCanonico> pedidos) {
        TypedQuery<Pedido> query = getEntityManager().createNamedQuery("Pedido.buscaPorNomeProduto", Pedido.class);
        for(PedidoCanonico numPedido : pedidos){
            query.setParameter("pnomeProduto", numPedido.getNomeProduto());
            try {
                return query.getSingleResult();
            } catch (NoResultException e) {
                return null;
            }
        }
        return null;
    }

    public List<PedidoCanonico> buscaPedidos(FiltroWrapper filtro) {
        PedidoFiltro pedidoFiltro = (PedidoFiltro) filtro.getFiltro();
        if (filtro.hasPaginacao()) {
            Long qtdRegistros = countRegistros(pedidoFiltro);
            if (qtdRegistros == 0) {
                return Lists.newArrayList();
            }
            filtro.getPaginacao().setTotalRegistros(qtdRegistros);
            CriteriaQuery<Long> criteria = criteriaQuery(Long.class);
            Root<Pedido> root = criteria.from(Pedido.class);
            criteria.distinct(true).select(root.get("pedidoID"))
                    .where(aplicaFiltros(root, pedidoFiltro, false)).orderBy(asc(root.get("pedidoID")));
            List<Long> resultado = paginarResultado(criteria, filtro);
            if (resultado.isEmpty()) {
                return Lists.newArrayList();
            }
            pedidoFiltro.setPedidoID(resultado);
        }
        return buscaClientes(pedidoFiltro);
    }

    private List<PedidoCanonico> buscaClientes(PedidoFiltro pedidoFiltro) {
        CriteriaQuery<Pedido> criteria = criteriaQuery(Pedido.class);
        Root<Pedido> root = criteria.from(Pedido.class);
        criteria.distinct(true).select(root).where(aplicaFiltros(root, pedidoFiltro, true))
                .orderBy(asc(root.get("pedidoID")));
        TypedQuery<Pedido> query = typedQuery(criteria);
        return pedidoCanonicoFactory.pedidosCanonico(query.getResultList());
    }

    private Long countRegistros(PedidoFiltro pedidoFiltro) {
        CriteriaQuery<Long> criteria = criteriaQuery(Long.class);
        Root<Pedido> root = criteria.from(Pedido.class);
        criteria.select(criteriaBuilder().countDistinct(root.get("pedidoID")))
                .where(aplicaFiltros(root, pedidoFiltro, false));
        return typedQuery(criteria).getSingleResult();
    }

    private Predicate[] aplicaFiltros(Root<Pedido> root, PedidoFiltro pedidoFiltro, boolean b) {
        CriteriaBuilder builder = criteriaBuilder();
        List<Predicate> predicates = Lists.newArrayList();

        // Implementar filtros
        return predicates.stream().toArray(Predicate[]::new);
    }

    public Pedido salvaPedido(Pedido pedido) {
        return merge(pedido);
    }
}
