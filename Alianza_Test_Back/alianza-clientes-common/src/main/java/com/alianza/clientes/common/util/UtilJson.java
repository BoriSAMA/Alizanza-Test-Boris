package com.alianza.clientes.common.util;

import com.fasterxml.jackson.databind.ObjectMapper;

public final class UtilJson {

    private UtilJson() {
        super();
    }

    /**
     *
     * @param json a codificar.
     * @param type de clase a convertir.
     * @return objeto codificado aparti de json.
     */
    public static <T> T toObject(String json, Class<T> type) {
        ObjectMapper mapperObj = new ObjectMapper();
        mapperObj.findAndRegisterModules();
        try {
            return mapperObj.readValue(json, type);
        }
        catch (Exception e) {
            // Swallow
        }
        return null;
    }

    /**
     * Codifica un objetos a json.
     * @param datos a codificar.
     * @return el objeto convertido a string.
     */
    public static String toString(Object datos) {
        ObjectMapper mapperObj = new ObjectMapper();
        mapperObj.findAndRegisterModules();

        try {
            return mapperObj.writeValueAsString(datos);
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
