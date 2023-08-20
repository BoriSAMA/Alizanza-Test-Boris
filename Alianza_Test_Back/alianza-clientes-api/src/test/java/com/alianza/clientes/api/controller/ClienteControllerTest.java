package com.alianza.clientes.api.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.alianza.clientes.api.service.ClienteService;
import com.alianza.clientes.common.response.IdResponse;
import com.alianza.clientes.core.domain.Cliente;
import com.alianza.clientes.core.dto.ClienteListResponse;
import com.alianza.clientes.core.filter.Page;

//@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ClienteControllerTest {

    @MockBean
    private ClienteService clienteService;

    @Autowired
    private TestRestTemplate restTemplate;

    // @Test
    public void should_Pass_InsertCliente() {

        // Given
        Cliente cliente = new Cliente();
        cliente.setName("cliente-test");
        Long clienteId = 1L;

        // When
        when(clienteService.insert(any())).thenReturn(clienteId);
        ResponseEntity<IdResponse> response = restTemplate.postForEntity("/clientes", cliente, IdResponse.class);

        // Then
        Assert.assertNotNull(response);
        Assert.assertEquals(HttpStatus.CREATED, response.getStatusCode());

        IdResponse idResponse = response.getBody();
        Assert.assertNotNull(idResponse);
        Assert.assertTrue(idResponse.isSuccess());
        Assert.assertEquals(HttpStatus.CREATED.value(), idResponse.getStatusCode());
        Assert.assertNotNull(idResponse.getData());
        Assert.assertEquals(clienteId, idResponse.getData());
    }

    // @Test
    public void should_Pass_GetCliente_Filter() {

        // Given
        Page<Cliente> page = new Page<>(0, new ArrayList<>());
    
        // when
        when(clienteService.getClientes(any())).thenReturn(page);

        // Assert
        ResponseEntity<ClienteListResponse> response = restTemplate.getForEntity("/clientes",
                ClienteListResponse.class);

        Assert.assertNotNull(response);
        Assert.assertEquals(HttpStatus.OK, response.getStatusCode());

        ClienteListResponse paginatedResponse = response.getBody();
        Assert.assertNotNull(paginatedResponse);
        Assert.assertEquals(HttpStatus.OK.value(), paginatedResponse.getStatusCode());
        Assert.assertTrue(paginatedResponse.isSuccess());
    }

}
