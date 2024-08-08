package com.cadastro.recepcao.pedido;

import com.cadastro.recepcao.pedido.entidade.Cliente;
import com.cadastro.recepcao.pedido.entidade.Pedido;
import com.cadastro.recepcao.pedido.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Component
public class ClienteInitial implements CommandLineRunner {

    @Autowired
    private ClienteRepository clienteRepository;

    @Override
    public void run(String... args) throws Exception {

        LocalDateTime dataAtual = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        // Cria e Salvar o Cliente 1
        Cliente cliente1 = new Cliente();
        cliente1.setNomeCliente("João Goncalves Castro");
        Cliente clientesalvo1 = clienteRepository.salvaCliente(cliente1);

        // Criar e salvar 3 pedidos
        Pedido pedido1 = new Pedido();
        pedido1.setDataCadastroPedido(dataAtual);
        pedido1.setNomeProduto("Produto 1");
        pedido1.setValorProduto(100.0);
        pedido1.setQuantidadeProduto(1);
        pedido1.setClienteID(clientesalvo1.getClienteID());
        pedido1.setCliente(clientesalvo1);

        Pedido pedido2 = new Pedido();
        pedido2.setDataCadastroPedido(dataAtual);
        pedido2.setNomeProduto("Produto 2");
        pedido2.setValorProduto(200.0);
        pedido2.setQuantidadeProduto(2);
        pedido2.setClienteID(clientesalvo1.getClienteID());
        pedido2.setCliente(clientesalvo1);

        Pedido pedido3 = new Pedido();
        pedido3.setDataCadastroPedido(dataAtual);
        pedido3.setNomeProduto("Produto 3");
        pedido3.setValorProduto(300.0);
        pedido3.setQuantidadeProduto(3);
        pedido3.setClienteID(clientesalvo1.getClienteID());
        pedido3.setCliente(clientesalvo1);

        List<Pedido> PedidosCliente1 = new ArrayList<>();
        PedidosCliente1.add(pedido1);
        PedidosCliente1.add(pedido2);
        PedidosCliente1.add(pedido3);

        clientesalvo1.setPedidos(PedidosCliente1);
        clienteRepository.merge(clientesalvo1);

        // ***********************************************************************

        // Cria e Salvar o Cliente 2
        Cliente cliente2 = new Cliente();
        cliente2.setNomeCliente("Felipe Almeida Melo");
        Cliente clientesalvo2 = clienteRepository.salvaCliente(cliente2);

        // Criar e salvar 3 pedidos
        Pedido pedido4 = new Pedido();
        pedido4.setDataCadastroPedido(dataAtual);
        pedido4.setNomeProduto("Produto 1");
        pedido4.setValorProduto(100.0);
        pedido4.setQuantidadeProduto(1);
        pedido4.setClienteID(clientesalvo2.getClienteID());
        pedido4.setCliente(clientesalvo2);

        Pedido pedido5 = new Pedido();
        pedido5.setDataCadastroPedido(dataAtual);
        pedido5.setNomeProduto("Produto 2");
        pedido5.setValorProduto(200.0);
        pedido5.setQuantidadeProduto(2);
        pedido5.setClienteID(clientesalvo2.getClienteID());
        pedido5.setCliente(clientesalvo2);

        Pedido pedido6 = new Pedido();
        pedido6.setDataCadastroPedido(dataAtual);
        pedido6.setNomeProduto("Produto 3");
        pedido6.setValorProduto(300.0);
        pedido6.setQuantidadeProduto(3);
        pedido6.setClienteID(clientesalvo2.getClienteID());
        pedido6.setCliente(clientesalvo2);

        List<Pedido> PedidosCliente2 = new ArrayList<>();
        PedidosCliente2.add(pedido4);
        PedidosCliente2.add(pedido5);
        PedidosCliente2.add(pedido6);

        clientesalvo2.setPedidos(PedidosCliente2);
        clienteRepository.merge(clientesalvo2);

        // ***********************************************************************

        // Cria e Salvar o Cliente 3
        Cliente cliente3 = new Cliente();
        cliente3.setNomeCliente("Luís Silva Barros");
        Cliente clientesalvo3 = clienteRepository.salvaCliente(cliente3);

        // Criar e salvar 3 pedidos
        Pedido pedido7 = new Pedido();
        pedido7.setDataCadastroPedido(dataAtual);
        pedido7.setNomeProduto("Produto 1");
        pedido7.setValorProduto(100.0);
        pedido7.setQuantidadeProduto(1);
        pedido7.setClienteID(clientesalvo3.getClienteID());
        pedido7.setCliente(clientesalvo3);

        Pedido pedido8 = new Pedido();
        pedido8.setDataCadastroPedido(dataAtual);
        pedido8.setNomeProduto("Produto 2");
        pedido8.setValorProduto(200.0);
        pedido8.setQuantidadeProduto(2);
        pedido8.setClienteID(clientesalvo3.getClienteID());
        pedido8.setCliente(clientesalvo3);

        Pedido pedido9 = new Pedido();
        pedido9.setDataCadastroPedido(dataAtual);
        pedido9.setNomeProduto("Produto 3");
        pedido9.setValorProduto(300.0);
        pedido9.setQuantidadeProduto(3);
        pedido9.setClienteID(clientesalvo3.getClienteID());
        pedido9.setCliente(clientesalvo3);

        List<Pedido> PedidosCliente3 = new ArrayList<>();
        PedidosCliente3.add(pedido7);
        PedidosCliente3.add(pedido8);
        PedidosCliente3.add(pedido9);

        clientesalvo3.setPedidos(PedidosCliente3);
        clienteRepository.merge(clientesalvo3);

        // ***********************************************************************

        // Cria e Salvar o Cliente 4
        Cliente cliente4 = new Cliente();
        cliente4.setNomeCliente("Sophia Lima Araujo");
        Cliente clientesalvo4 = clienteRepository.salvaCliente(cliente4);

        // Criar e salvar 3 pedidos
        Pedido pedido10 = new Pedido();
        pedido10.setDataCadastroPedido(dataAtual);
        pedido10.setNomeProduto("Produto 1");
        pedido10.setValorProduto(100.0);
        pedido10.setQuantidadeProduto(1);
        pedido10.setClienteID(clientesalvo4.getClienteID());
        pedido10.setCliente(clientesalvo4);

        Pedido pedido11 = new Pedido();
        pedido11.setDataCadastroPedido(dataAtual);
        pedido11.setNomeProduto("Produto 2");
        pedido11.setValorProduto(200.0);
        pedido11.setQuantidadeProduto(2);
        pedido11.setClienteID(clientesalvo4.getClienteID());
        pedido11.setCliente(clientesalvo4);

        Pedido pedido12 = new Pedido();
        pedido12.setDataCadastroPedido(dataAtual);
        pedido12.setNomeProduto("Produto 3");
        pedido12.setValorProduto(300.0);
        pedido12.setQuantidadeProduto(3);
        pedido12.setClienteID(clientesalvo4.getClienteID());
        pedido12.setCliente(clientesalvo4);

        List<Pedido> PedidosCliente4 = new ArrayList<>();
        PedidosCliente4.add(pedido10);
        PedidosCliente4.add(pedido11);
        PedidosCliente4.add(pedido12);

        clientesalvo4.setPedidos(PedidosCliente4);
        clienteRepository.merge(clientesalvo4);

        // ***********************************************************************

        // Cria e Salvar o Cliente 5
        Cliente cliente5 = new Cliente();
        cliente5.setNomeCliente("André Cavalcanti Goncalves");
        Cliente clientesalvo5 = clienteRepository.salvaCliente(cliente5);

        // Criar e salvar 3 pedidos
        Pedido pedido13 = new Pedido();
        pedido13.setDataCadastroPedido(dataAtual);
        pedido13.setNomeProduto("Produto 1");
        pedido13.setValorProduto(100.0);
        pedido13.setQuantidadeProduto(1);
        pedido13.setClienteID(clientesalvo5.getClienteID());
        pedido13.setCliente(clientesalvo5);

        Pedido pedido14 = new Pedido();
        pedido14.setDataCadastroPedido(dataAtual);
        pedido14.setNomeProduto("Produto 2");
        pedido14.setValorProduto(200.0);
        pedido14.setQuantidadeProduto(2);
        pedido14.setClienteID(clientesalvo5.getClienteID());
        pedido14.setCliente(clientesalvo5);

        Pedido pedido15 = new Pedido();
        pedido15.setDataCadastroPedido(dataAtual);
        pedido15.setNomeProduto("Produto 3");
        pedido15.setValorProduto(300.0);
        pedido15.setQuantidadeProduto(3);
        pedido15.setClienteID(clientesalvo5.getClienteID());
        pedido15.setCliente(clientesalvo5);

        List<Pedido> PedidosCliente5 = new ArrayList<>();
        PedidosCliente5.add(pedido13);
        PedidosCliente5.add(pedido14);
        PedidosCliente5.add(pedido15);

        clientesalvo5.setPedidos(PedidosCliente5);
        clienteRepository.merge(clientesalvo5);

        // ***********************************************************************

        // Cria e Salvar o Cliente 6
        Cliente cliente6 = new Cliente();
        cliente6.setNomeCliente("André Silva Gomes");
        Cliente clientesalvo6 = clienteRepository.salvaCliente(cliente6);

        // Criar e salvar 3 pedidos
        Pedido pedido16 = new Pedido();
        pedido16.setDataCadastroPedido(dataAtual);
        pedido16.setNomeProduto("Produto 1");
        pedido16.setValorProduto(100.0);
        pedido16.setQuantidadeProduto(1);
        pedido16.setClienteID(clientesalvo6.getClienteID());
        pedido16.setCliente(clientesalvo6);

        Pedido pedido17 = new Pedido();
        pedido17.setDataCadastroPedido(dataAtual);
        pedido17.setNomeProduto("Produto 2");
        pedido17.setValorProduto(200.0);
        pedido17.setQuantidadeProduto(2);
        pedido17.setClienteID(clientesalvo6.getClienteID());
        pedido17.setCliente(clientesalvo6);

        Pedido pedido18 = new Pedido();
        pedido18.setDataCadastroPedido(dataAtual);
        pedido18.setNomeProduto("Produto 3");
        pedido18.setValorProduto(300.0);
        pedido18.setQuantidadeProduto(3);
        pedido18.setClienteID(clientesalvo6.getClienteID());
        pedido18.setCliente(clientesalvo6);

        List<Pedido> PedidosCliente6 = new ArrayList<>();
        PedidosCliente6.add(pedido16);
        PedidosCliente6.add(pedido17);
        PedidosCliente6.add(pedido18);

        clientesalvo6.setPedidos(PedidosCliente6);
        clienteRepository.merge(clientesalvo6);

        // ***********************************************************************

        // Cria e Salvar o Cliente 7
        Cliente cliente7 = new Cliente();
        cliente7.setNomeCliente("Beatriz Alves Almeida");
        Cliente clientesalvo7 = clienteRepository.salvaCliente(cliente7);

        // Criar e salvar 3 pedidos
        Pedido pedido19 = new Pedido();
        pedido19.setDataCadastroPedido(dataAtual);
        pedido19.setNomeProduto("Produto 1");
        pedido19.setValorProduto(100.0);
        pedido19.setQuantidadeProduto(1);
        pedido19.setClienteID(clientesalvo7.getClienteID());
        pedido19.setCliente(clientesalvo7);

        Pedido pedido20 = new Pedido();
        pedido20.setDataCadastroPedido(dataAtual);
        pedido20.setNomeProduto("Produto 2");
        pedido20.setValorProduto(200.0);
        pedido20.setQuantidadeProduto(2);
        pedido20.setClienteID(clientesalvo7.getClienteID());
        pedido20.setCliente(clientesalvo7);

        Pedido pedido21 = new Pedido();
        pedido21.setDataCadastroPedido(dataAtual);
        pedido21.setNomeProduto("Produto 3");
        pedido21.setValorProduto(300.0);
        pedido21.setQuantidadeProduto(3);
        pedido21.setClienteID(clientesalvo7.getClienteID());
        pedido21.setCliente(clientesalvo7);

        List<Pedido> PedidosCliente7 = new ArrayList<>();
        PedidosCliente7.add(pedido19);
        PedidosCliente7.add(pedido20);
        PedidosCliente7.add(pedido21);

        clientesalvo7.setPedidos(PedidosCliente7);
        clienteRepository.merge(clientesalvo7);

        // ***********************************************************************

        // Cria e Salvar o Cliente 8
        Cliente cliente8 = new Cliente();
        cliente8.setNomeCliente("Rafaela Oliveira Almeida");
        Cliente clientesalvo8 = clienteRepository.salvaCliente(cliente8);

        // Criar e salvar 3 pedidos
        Pedido pedido22 = new Pedido();
        pedido22.setDataCadastroPedido(dataAtual);
        pedido22.setNomeProduto("Produto 1");
        pedido22.setValorProduto(100.0);
        pedido22.setQuantidadeProduto(1);
        pedido22.setClienteID(clientesalvo8.getClienteID());
        pedido22.setCliente(clientesalvo8);

        Pedido pedido23 = new Pedido();
        pedido23.setDataCadastroPedido(dataAtual);
        pedido23.setNomeProduto("Produto 2");
        pedido23.setValorProduto(200.0);
        pedido23.setQuantidadeProduto(2);
        pedido23.setClienteID(clientesalvo8.getClienteID());
        pedido23.setCliente(clientesalvo8);

        Pedido pedido24 = new Pedido();
        pedido24.setDataCadastroPedido(dataAtual);
        pedido24.setNomeProduto("Produto 3");
        pedido24.setValorProduto(300.0);
        pedido24.setQuantidadeProduto(3);
        pedido24.setClienteID(clientesalvo8.getClienteID());
        pedido24.setCliente(clientesalvo8);

        List<Pedido> PedidosCliente8 = new ArrayList<>();
        PedidosCliente8.add(pedido21);
        PedidosCliente8.add(pedido23);
        PedidosCliente8.add(pedido24);

        clientesalvo8.setPedidos(PedidosCliente8);
        clienteRepository.merge(clientesalvo8);

        // ***********************************************************************

        // Cria e Salvar o Cliente 9
        Cliente cliente9 = new Cliente();
        cliente9.setNomeCliente("Ana Pereira Almeida");
        Cliente clientesalvo9 = clienteRepository.salvaCliente(cliente9);

        // Criar e salvar 3 pedidos
        Pedido pedido25 = new Pedido();
        pedido25.setDataCadastroPedido(dataAtual);
        pedido25.setNomeProduto("Produto 1");
        pedido25.setValorProduto(100.0);
        pedido25.setQuantidadeProduto(1);
        pedido25.setClienteID(clientesalvo9.getClienteID());
        pedido25.setCliente(clientesalvo9);

        Pedido pedido26 = new Pedido();
        pedido26.setDataCadastroPedido(dataAtual);
        pedido26.setNomeProduto("Produto 2");
        pedido26.setValorProduto(200.0);
        pedido26.setQuantidadeProduto(2);
        pedido26.setClienteID(clientesalvo9.getClienteID());
        pedido26.setCliente(clientesalvo9);

        Pedido pedido27 = new Pedido();
        pedido27.setDataCadastroPedido(dataAtual);
        pedido27.setNomeProduto("Produto 3");
        pedido27.setValorProduto(300.0);
        pedido27.setQuantidadeProduto(3);
        pedido27.setClienteID(clientesalvo9.getClienteID());
        pedido27.setCliente(clientesalvo9);

        List<Pedido> PedidosCliente9 = new ArrayList<>();
        PedidosCliente9.add(pedido25);
        PedidosCliente9.add(pedido26);
        PedidosCliente9.add(pedido27);

        clientesalvo9.setPedidos(PedidosCliente9);
        clienteRepository.merge(clientesalvo9);

        // ***********************************************************************

        // Cria e Salvar o Cliente 10
        Cliente cliente10 = new Cliente();
        cliente10.setNomeCliente("Estevan Souza Cunha");
        Cliente clientesalvo10 = clienteRepository.salvaCliente(cliente10);

        // Criar e salvar 3 pedidos
        Pedido pedido28 = new Pedido();
        pedido28.setDataCadastroPedido(dataAtual);
        pedido28.setNomeProduto("Produto 1");
        pedido28.setValorProduto(100.0);
        pedido28.setQuantidadeProduto(1);
        pedido28.setClienteID(clientesalvo10.getClienteID());
        pedido28.setCliente(clientesalvo10);

        Pedido pedido29 = new Pedido();
        pedido29.setDataCadastroPedido(dataAtual);
        pedido29.setNomeProduto("Produto 2");
        pedido29.setValorProduto(200.0);
        pedido29.setQuantidadeProduto(2);
        pedido29.setClienteID(clientesalvo10.getClienteID());
        pedido29.setCliente(clientesalvo10);

        Pedido pedido30 = new Pedido();
        pedido30.setDataCadastroPedido(dataAtual);
        pedido30.setNomeProduto("Produto 3");
        pedido30.setValorProduto(300.0);
        pedido30.setQuantidadeProduto(3);
        pedido30.setClienteID(clientesalvo10.getClienteID());
        pedido30.setCliente(clientesalvo10);

        List<Pedido> PedidosCliente10 = new ArrayList<>();
        PedidosCliente10.add(pedido28);
        PedidosCliente10.add(pedido29);
        PedidosCliente10.add(pedido30);

        clientesalvo10.setPedidos(PedidosCliente10);
        clienteRepository.merge(clientesalvo10);
    }
}
