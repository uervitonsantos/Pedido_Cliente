package com.cadastro.recepcao.pedido.controllerImplements;

import com.cadastro.recepcao.pedido.bean.ClienteBean;
import com.cadastro.recepcao.pedido.canonico.ClienteCanonico;
import com.cadastro.recepcao.pedido.dto.ClienteDTO;
import com.cadastro.recepcao.pedido.dtoFactory.ClienteDTOFactory;
import com.cadastro.recepcao.pedido.filterDTO.ClienteFiltroDTO;
import com.cadastro.recepcao.pedido.filtro.FiltroWrapper;
import com.cadastro.recepcao.pedido.recurso.ClienteController;
import com.cadastro.recepcao.pedido.util.constates.Resource;
import jakarta.ws.rs.BeanParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/cliente")
public class ClienteControllerImpl implements ClienteController {

    private static final String PATH_ID = "/{clienteID}";

    @Autowired
    private ClienteBean clienteBean;

    @Autowired
    private ClienteDTOFactory clienteDTOFactory;

    @GetMapping(PATH_ID)
    @Override
    public ResponseEntity<ClienteDTO> getCliente(@PathVariable(Resource.P_CLIENTE_ID) Long clienteID) {
        ClienteDTO dto = clienteDTOFactory.builderClienteDto(clienteBean.buscaCliente(clienteID));
        return ResponseEntity.ok().body(dto);
    }

    @GetMapping
    @Override
    public ResponseEntity<List<ClienteDTO>> getClientes(@BeanParam ClienteFiltroDTO filtro) {
        FiltroWrapper wrapper = filtro.filtroWrapper();
        List<ClienteCanonico> clientes = clienteBean.buscaClientes(wrapper);
        return ResponseEntity.ok(clienteDTOFactory.clientesDto(clientes));
    }

    @PostMapping
    @Override
    public ResponseEntity<ClienteDTO> criaCliente(@RequestBody ClienteDTO dto) {
        ClienteCanonico canonico = clienteDTOFactory.builderClienteCanonico(dto);
        ClienteCanonico clienteSalva = clienteBean.criaCliente(canonico);
        return criaResponse(clienteSalva);
    }

    @PutMapping(PATH_ID)
    @Override
    public ResponseEntity atualizaCliente(@PathVariable(Resource.P_CLIENTE_ID) Long clienteID, @RequestBody ClienteDTO dto) {
        dto.setClienteID(clienteID);
        ClienteCanonico canonico = clienteDTOFactory.builderClienteCanonico(dto);
        ClienteCanonico clienteAtualizado = clienteBean.editaCliente(canonico);
        return criaResponse(clienteAtualizado);
    }

    private ResponseEntity criaResponse(@RequestBody ClienteCanonico cliente) {
        ClienteDTO clienteDTO = clienteDTOFactory.builderClienteDto(clienteBean.buscaCliente(cliente.getClienteID()));
        return ResponseEntity.ok().body(clienteDTO);
    }

    @DeleteMapping(PATH_ID)
    @Override
    public ResponseEntity removeCliente(@PathVariable(Resource.P_CLIENTE_ID) Long clienteID) {
        clienteBean.removeCliente(clienteID);
        return ResponseEntity.noContent().build();
    }
}
