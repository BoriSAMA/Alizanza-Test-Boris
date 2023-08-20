package com.alianza.clientes.api.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.alianza.clientes.api.dao.ClienteDao;
import com.alianza.clientes.api.entity.ClienteEntity;
import com.alianza.clientes.api.service.impl.ClienteServiceImpl;
import com.alianza.clientes.core.domain.Cliente;
import com.alianza.clientes.core.filter.ClienteFilter;
import com.alianza.clientes.core.filter.Page;

@RunWith(MockitoJUnitRunner.class)
public class ClienteServiceTest {

    @Mock
    private ConverterService converterService;

    @Mock
    private ClienteDao clienteDao;

    @InjectMocks
    private ClienteServiceImpl clienteService;

    @Test
    public void should_Pass_InsertCliente() {
        // Given
        Cliente cliente = mock(Cliente.class);
        ClienteEntity entity = mock(ClienteEntity.class);
        Long clienteId = 1L;

        // When
        when(entity.getId()).thenReturn(clienteId);
        when(converterService.convertTo(cliente, ClienteEntity.class)).thenReturn(entity);
        Long resultId = clienteService.insert(cliente);

        // Then
        Assert.assertNotNull(resultId);
        Assert.assertEquals(clienteId, resultId);
        verify(converterService, times(1)).convertTo(cliente, ClienteEntity.class);
        verify(clienteDao, times(1)).save(entity);
    }

    @Test
    public void should_Pass_GetClientes() {
        Page<ClienteEntity> page = mock(Page.class);
        ClienteEntity entity = mock(ClienteEntity.class);
        List<ClienteEntity> listEntities = Collections.singletonList(entity);
        Cliente bean = mock(Cliente.class);
        List<Cliente> listBeans = Collections.singletonList(bean);

        when(clienteDao.getPage(any(ClienteFilter.class))).thenReturn(page);
        when(page.getContent()).thenReturn(listEntities);
        when(converterService.convertTo(anyList(), eq(Cliente.class))).thenReturn(listBeans);
        Page<Cliente> pageResponse = clienteService.getClientes(any(ClienteFilter.class));

        Assert.assertNotNull(pageResponse);
        verify(clienteDao, times(1)).getPage(any(ClienteFilter.class));
        verify(converterService, times(1)).convertTo(anyList(), eq(Cliente.class));
    }
}
