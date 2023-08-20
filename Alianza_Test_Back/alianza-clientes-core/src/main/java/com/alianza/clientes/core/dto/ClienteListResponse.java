package com.alianza.clientes.core.dto;

import java.util.List;

import com.alianza.clientes.common.response.ListResponse;
import com.alianza.clientes.core.domain.Cliente;

public class ClienteListResponse extends ListResponse<Cliente> {

    public ClienteListResponse() {
        super();
    }

    public ClienteListResponse(List<Cliente> clientes) {
        super(clientes);
    }

    public ClienteListResponse(long count, List<Cliente> clientes) {
        super(clientes, count);
    }
}
