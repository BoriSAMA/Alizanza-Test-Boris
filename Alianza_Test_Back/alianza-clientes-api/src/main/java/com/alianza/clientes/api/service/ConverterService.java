package com.alianza.clientes.api.service;

import java.util.List;

/**
 * Servicio que permite convertir de un objeto a otro.
 */
public interface ConverterService {

    /**
     * Convierte una entidad del tipo S al tipo T.
     *
     * @param source objeto origen.
     * @param targetClass Clase destino.
     * @param <S> El tipo de dato del objeto source
     * @param <T> El tipo de dato del objeto target.
     * @return instancia de T con el resultado de la conversiÛn de S a T.
     */
    <S, T> T convertTo(S source, Class<T> targetClass);

    /**
     * Convierte una lista de objetos source al tipo target.
     * @param source La lista de objetos origen.
     * @param targetClass El tipo de objeto target.
     * @param <S> El tipo de objeto origen.
     * @param <T> EL tipo de objeto target.
     * @return Una lista de objetos.
     */
    <S, T> List<T> convertTo(List<S> source, Class<T> targetClass);

    /**
     * Determina si existe un converter v·lido para los tipos S y T.
     *
     * @param sourceClass tipo de objeto origen.
     * @param targetClass tipo de objeto destino.
     * @param <S> El tipo de objeto origen.
     * @param <T> EL tipo de objeto target.
     * @return {@code true} si la conversion es posible.
     */
    <S, T> boolean canConvertTo(Class<S> sourceClass, Class<T> targetClass);

}
