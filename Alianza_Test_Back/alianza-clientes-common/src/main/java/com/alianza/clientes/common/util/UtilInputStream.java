package com.alianza.clientes.common.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public final class UtilInputStream {

    private UtilInputStream() {
        super();
    }

    public static String getString(InputStream stream) throws IOException {
        StringBuilder inputStringBuilder = new StringBuilder();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(stream, StandardCharsets.UTF_8));
        String line = bufferedReader.readLine();
        while (line != null) {
            inputStringBuilder.append(line);
            inputStringBuilder.append('\n');
            line = bufferedReader.readLine();
        }
        return inputStringBuilder.toString();
    }

    public static String getStringSilent(InputStream stream) {
        try {
            return getString(stream);
        }
        catch (Exception e) {
            return null;
        }
    }
}
