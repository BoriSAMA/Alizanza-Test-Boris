package com.alianza.clientes.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = {
        "com.alianza.clientes.api.config",
        "com.alianza.clientes.api.controller",
        "com.alianza.clientes.api.service",
        "com.alianza.clientes.api.dao",
        "com.alianza.clientes.api.converter",
        "com.alianza.clientes.api.exception"
})
@SpringBootApplication
public class ClientesApp extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(ClientesApp.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(ClientesApp.class, args);
    }

}
