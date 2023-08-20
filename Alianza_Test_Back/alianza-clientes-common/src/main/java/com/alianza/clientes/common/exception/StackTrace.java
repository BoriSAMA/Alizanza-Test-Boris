package com.alianza.clientes.common.exception;

import java.io.PrintWriter;
import java.io.StringWriter;

public final class StackTrace {

    private StackTrace() {
        super();
    }

    public static String getStackTrace(Throwable throwable) {
        if (throwable == null) {
            return null;
        }
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        throwable.printStackTrace(pw);
        return sw.toString();
    }

}
