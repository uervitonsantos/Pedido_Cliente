package com.cadastro.recepcao.pedido.recurso;

import com.cadastro.recepcao.pedido.dto.PedidoDTO;
import com.cadastro.recepcao.pedido.filterDTO.PedidoFiltroDTO;
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

@Tag(name = "Pedido", description = "Operações para os recursos de pedidos")
public interface PedidoController extends Resource {

    @Operation(summary = "Retorna o pedido consultada pelo codigo identificador (id)", description = "Retorna um Pedido cadastrado", responses = {
            @ApiResponse(responseCode = Resource.RESPONSE_OK, description = "Sucesso", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(description = "Objeto Json retornado na busca do pedido", implementation = PedidoDTO.class))),
            @ApiResponse(responseCode = Resource.RESPONSE_BAD_REQUEST, description = "Código do Pedido invalido", content = @Content),
            @ApiResponse(responseCode = Resource.RESPONSE_UNAUTHORIZED, description = "Pedido não autorizado", content = @Content),
            @ApiResponse(responseCode = Resource.RESPONSE_FORBIDDEN, description = "Pedido não autorizado", content = @Content)})
    ResponseEntity getPedido(@Parameter(description = "Código identificador do Pedido") Long pedidoID);

    @Operation(summary = "Retorna lista de Pedidos", description = "Retorna uma lista de todos os Pedidos cadastrados", responses = {
            @ApiResponse(responseCode = Resource.RESPONSE_OK, description = "Sucesso", content = @Content(mediaType = MediaType.APPLICATION_JSON, array = @ArraySchema(schema = @Schema(description = "Objeto Json retornado na busca do pedido", implementation = PedidoDTO.class)))),
            @ApiResponse(responseCode = Resource.RESPONSE_UNAUTHORIZED, description = "Pedido não autorizado", content = @Content),
            @ApiResponse(responseCode = Resource.RESPONSE_FORBIDDEN, description = "Pedido não autorizado", content = @Content)})
    ResponseEntity getPedidos(@Parameter(description = "Filtros permitidos para a buscar de pedido") PedidoFiltroDTO filtro);

    @Operation(summary = "Cria um novo pedido", description = "Cria um novo pedido e adiciona a lista de pedidos cadastrados",
            requestBody = @RequestBody(description = "Dados necessarios para a  criação de um novo pedido", required = true,
                    content = @Content(mediaType = MediaType.APPLICATION_JSON)), responses = {
            @ApiResponse(responseCode = Resource.RESPONSE_OK, description = "Sucesso.", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(description = "Objeto Json retornado na criação do pedidos", implementation = PedidoDTO.class))),
            @ApiResponse(responseCode = Resource.RESPONSE_BAD_REQUEST, description = "Erro ao criar novo pedido", content = @Content)})
    ResponseEntity criaPedido(PedidoDTO dto);

    @Operation(summary = "Atualiza um pedido", description = "Atualiza um pedido cadastrado",
            requestBody = @RequestBody(description = "Dados necessarios para a  atualização de um pedido", required = true,
                    content = @Content(mediaType = MediaType.APPLICATION_JSON)),
            responses = {
                    @ApiResponse(responseCode = Resource.RESPONSE_OK, description = "Sucesso", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(description = "Objeto Json retornado na atualização do pedido", implementation = PedidoDTO.class))),
                    @ApiResponse(responseCode = Resource.RESPONSE_BAD_REQUEST, description = "Erro ao atualizar o pedido", content = @Content),
                    @ApiResponse(responseCode = Resource.RESPONSE_UNAUTHORIZED, description = "Pedido não autorizado", content = @Content),
                    @ApiResponse(responseCode = Resource.RESPONSE_FORBIDDEN, description = "Pedido não autorizado", content = @Content)})
    ResponseEntity atualizaPedido(@Parameter(description = "Código identificador do pedido") Long pedidoID, PedidoDTO dto);

    @Operation(summary = "Remove um pedido", description = "Remove um pedido cadastrado", responses = {
            @ApiResponse(responseCode = Resource.RESPONSE_NO_CONTENT, description = "Pedido removido com sucesso"),
            @ApiResponse(responseCode = Resource.RESPONSE_BAD_REQUEST, description = "Erro ao remover pedido", content = @Content),
            @ApiResponse(responseCode = Resource.RESPONSE_UNAUTHORIZED, description = "Pedido não autorizado", content = @Content),
            @ApiResponse(responseCode = Resource.RESPONSE_FORBIDDEN, description = "Pedido não autorizado", content = @Content)})
    ResponseEntity removePedido(@Parameter(description = "Código identificador do pedido") Long pedidoID);
}
