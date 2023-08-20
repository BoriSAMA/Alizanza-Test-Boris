package com.alianza.clientes.api.controller;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alianza.clientes.api.service.ClienteService;
import com.alianza.clientes.common.response.IdResponse;
import com.alianza.clientes.common.response.Responses;
import com.alianza.clientes.core.domain.Cliente;
import com.alianza.clientes.core.dto.ClienteListResponse;
import com.alianza.clientes.core.filter.ClienteFilter;
import com.alianza.clientes.core.filter.Page;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(path = "/clients")
@Api(value = "Controller de clientes",
        description = "Controller que contiene los endpoints para manejo de clientes.")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @ApiOperation(value = "Permite insertar un cliente.")
    @ApiResponses({@ApiResponse(code = 201, message = "Created")})
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<IdResponse> insert(@NotNull @RequestBody Cliente cliente) {
        return Responses.responseEntity(new IdResponse(clienteService.insert(cliente)));
    }

    @ApiOperation(value = "Obtiene todos los clientes registrados dado un filtro.")
    @ApiResponses({@ApiResponse(code = 200, message = "OK")})
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ClienteListResponse> getClientes(ClienteFilter clienteFilter) {
        Page<Cliente> clientes = clienteService.getClientes(clienteFilter);
        return Responses.responseEntity(new ClienteListResponse(clientes.getTotalElements(), clientes.getContent()));
    }

}
