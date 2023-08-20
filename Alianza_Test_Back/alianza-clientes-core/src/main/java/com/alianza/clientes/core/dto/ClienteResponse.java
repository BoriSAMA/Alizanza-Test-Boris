package com.alianza.clientes.core.dto;

import com.alianza.clientes.common.response.ObjectResponse;
import com.alianza.clientes.core.domain.Cliente;

public class ClienteResponse extends ObjectResponse<Cliente> {

    public ClienteResponse() {
        super();
    }

    public ClienteResponse(Cliente cliente) {
        super(cliente);
    }

}
