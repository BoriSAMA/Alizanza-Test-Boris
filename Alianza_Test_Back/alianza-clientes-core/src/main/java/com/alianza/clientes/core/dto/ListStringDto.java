package com.alianza.clientes.core.dto;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

public class ListStringDto {

    @Valid
    @NotEmpty
    private List<String> listString;

    public List<String> getListString() {
        return listString;
    }

    public void setListString(List<String> listString) {
        this.listString = listString;
    }
}
