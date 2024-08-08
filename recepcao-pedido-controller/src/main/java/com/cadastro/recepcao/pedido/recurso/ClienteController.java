package com.cadastro.recepcao.pedido.recurso;

import com.cadastro.recepcao.pedido.dto.ClienteDTO;
import com.cadastro.recepcao.pedido.filterDTO.ClienteFiltroDTO;
import com.cadastro.recepcao.pedido.util.constates.Resource;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.ws.rs.core.MediaType;
import org.springframework.http.ResponseEntity;

@Tag(name = "Cliente", description = "Operações para os recursos de clientes")
public interface ClienteController extends Resource {

    @Operation(summary = "Retorna o cliente consultada pelo codigo identificador (id)", description = "Retorna um Cliente cadastrado", responses = {
            @ApiResponse(responseCode = Resource.RESPONSE_OK, description = "Sucesso", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(description = "Objeto Json retornado na busca do cliente", implementation = ClienteDTO.class))),
            @ApiResponse(responseCode = Resource.RESPONSE_BAD_REQUEST, description = "Código do Cliente invalido", content = @Content),
            @ApiResponse(responseCode = Resource.RESPONSE_UNAUTHORIZED, description = "Cliente não autorizado", content = @Content),
            @ApiResponse(responseCode = Resource.RESPONSE_FORBIDDEN, description = "Cliente não autorizado", content = @Content)})
    ResponseEntity getCliente(@Parameter(description = "Código identificador do Cliente") Long clienteID);

    @Operation(summary = "Retorna lista de Clientes", description = "Retorna uma lista de todos os Clientes cadastrados", responses = {
            @ApiResponse(responseCode = Resource.RESPONSE_OK, description = "Sucesso", content = @Content(mediaType = MediaType.APPLICATION_JSON, array = @ArraySchema(schema = @Schema(description = "Objeto Json retornado na busca do cliente", implementation = ClienteDTO.class)))),
            @ApiResponse(responseCode = Resource.RESPONSE_UNAUTHORIZED, description = "Cliente não autorizado", content = @Content),
            @ApiResponse(responseCode = Resource.RESPONSE_FORBIDDEN, description = "Cliente não autorizado", content = @Content)})
    ResponseEntity getClientes(@Parameter(description = "Filtros permitidos para a buscar de cliente") ClienteFiltroDTO filtro);

    @Operation(summary = "Cria um novo cliente", description = "Cria um novo cliente e adiciona a lista de clientes cadastrados",
            requestBody = @RequestBody(description = "Dados necessarios para a  criação de um novo cliente", required = true,
                    content = @Content(mediaType = MediaType.APPLICATION_JSON)), responses = {
            @ApiResponse(responseCode = Resource.RESPONSE_OK, description = "Sucesso.", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(description = "Objeto Json retornado na criação do clientes", implementation = ClienteDTO.class))),
            @ApiResponse(responseCode = Resource.RESPONSE_BAD_REQUEST, description = "Erro ao criar novo cliente", content = @Content)})
    ResponseEntity criaCliente(ClienteDTO dto);

    @Operation(summary = "Atualiza um cliente", description = "Atualiza um cliente cadastrado",
            requestBody = @RequestBody(description = "Dados necessarios para a  atualização de um cliente", required = true,
                    content = @Content(mediaType = MediaType.APPLICATION_JSON)),
            responses = {
                    @ApiResponse(responseCode = Resource.RESPONSE_OK, description = "Sucesso", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(description = "Objeto Json retornado na atualização do cliente", implementation = ClienteDTO.class))),
                    @ApiResponse(responseCode = Resource.RESPONSE_BAD_REQUEST, description = "Erro ao atualizar o cliente", content = @Content),
                    @ApiResponse(responseCode = Resource.RESPONSE_UNAUTHORIZED, description = "Cliente não autorizado", content = @Content),
                    @ApiResponse(responseCode = Resource.RESPONSE_FORBIDDEN, description = "Cliente não autorizado", content = @Content)})
    ResponseEntity atualizaCliente(@Parameter(description = "Código identificador do cliente") Long clienteID, ClienteDTO dto);

    @Operation(summary = "Remove um cliente", description = "Remove um cliente cadastrado", responses = {
            @ApiResponse(responseCode = Resource.RESPONSE_NO_CONTENT, description = "Cliente removido com sucesso"),
            @ApiResponse(responseCode = Resource.RESPONSE_BAD_REQUEST, description = "Erro ao remover cliente", content = @Content),
            @ApiResponse(responseCode = Resource.RESPONSE_UNAUTHORIZED, description = "Cliente não autorizado", content = @Content),
            @ApiResponse(responseCode = Resource.RESPONSE_FORBIDDEN, description = "Cliente não autorizado", content = @Content)})
    ResponseEntity removeCliente(@Parameter(description = "Código identificador do cliente") Long clienteID);
}
