package com.alianza.clientes.api.service;

import com.alianza.clientes.core.domain.Cliente;
import com.alianza.clientes.core.filter.Page;
import com.alianza.clientes.core.filter.ClienteFilter;

public interface ClienteService extends Service<Cliente, Long> {

    Page<Cliente> getClientes(ClienteFilter clienteFilter);

}
