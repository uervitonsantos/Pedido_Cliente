package com.cadastro.recepcao.pedido.controllerImplements;

import com.cadastro.recepcao.pedido.bean.PedidoBean;
import com.cadastro.recepcao.pedido.canonico.PedidoCanonico;
import com.cadastro.recepcao.pedido.dto.PedidoDTO;
import com.cadastro.recepcao.pedido.dtoFactory.PedidoDTOFactory;
import com.cadastro.recepcao.pedido.filterDTO.PedidoFiltroDTO;
import com.cadastro.recepcao.pedido.filtro.FiltroWrapper;
import com.cadastro.recepcao.pedido.recurso.PedidoController;
import com.cadastro.recepcao.pedido.util.constates.Resource;
import jakarta.ws.rs.BeanParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/pedido")
public class PedidoControllerImpl implements PedidoController {

    private static final String PATH_ID = "/{pedidoID}";

    @Autowired
    private PedidoBean pedidoBean;

    @Autowired
    private PedidoDTOFactory pedidoDTOFactory;

    @GetMapping(PATH_ID)
    @Override
    public ResponseEntity<PedidoDTO> getPedido(@PathVariable(Resource.P_PEDIDO_ID) Long pedidoID) {
        PedidoDTO dto = pedidoDTOFactory.builderPedidoDto(pedidoBean.buscaPedido(pedidoID));
        return ResponseEntity.ok().body(dto);
    }

    @GetMapping
    @Override
    public ResponseEntity<List<PedidoDTO>> getPedidos(@BeanParam PedidoFiltroDTO filtro) {
        FiltroWrapper wrapper = filtro.filtroWrapper();
        List<PedidoCanonico> pedidos = pedidoBean.buscaPedidos(wrapper);
        return ResponseEntity.ok(pedidoDTOFactory.pedidosDto(pedidos));
    }

    @PostMapping
    @Override
    public ResponseEntity<PedidoDTO> criaPedido(@RequestBody PedidoDTO dto) {
        PedidoCanonico canonico = pedidoDTOFactory.builderPedidoCanonico(dto);
        PedidoCanonico pedidoSalva = pedidoBean.criaPedido(canonico);
        return criaResponse(pedidoSalva);
    }

    @PutMapping(PATH_ID)
    @Override
    public ResponseEntity atualizaPedido(@PathVariable(Resource.P_PEDIDO_ID) Long pedidoID, @RequestBody PedidoDTO dto) {
        dto.setPedidoID(pedidoID);
        PedidoCanonico canonico = pedidoDTOFactory.builderPedidoCanonico(dto);
        PedidoCanonico pedidoAtualizado = pedidoBean.editaPedido(canonico);
        return criaResponse(pedidoAtualizado);
    }

    private ResponseEntity criaResponse(@RequestBody PedidoCanonico pedido) {
        PedidoDTO pedidoDTO = pedidoDTOFactory.builderPedidoDto(pedidoBean.buscaPedido(pedido.getPedidoID()));
        return ResponseEntity.ok().body(pedidoDTO);
    }

    @DeleteMapping(PATH_ID)
    @Override
    public ResponseEntity removePedido(@PathVariable(Resource.P_PEDIDO_ID) Long pedidoID) {
        pedidoBean.removePedido(pedidoID);
        return ResponseEntity.noContent().build();
    }
}
