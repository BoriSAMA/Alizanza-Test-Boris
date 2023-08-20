package com.alianza.clientes.common.util;

import java.util.Locale;

public interface Constants {

    // UtilDateService
    String FORMAT_DATE = "yyyy-MM-dd";
    String FORMAT_DATE_TIME = "yyyy-MM-dd HH:mm:ss";
    String FORMAT_DATE_TIME_ISO = "yyyy-MM-dd'T'HH:mm:ss'Z'";
    String FORMAT_TIME = "HH:mm:ss";
    String FORMAT_LOCAL_TIME_HM = "HH:mm";

    // ExcelService
    char COMMA_SEPARATOR = ',';

    // MailService
    String PATTERN_EMAIL = "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$";

    // ObjectService
    String PATTERN_DECIMAL = "#,##0.00";
    Locale LOCALE = new Locale("ES", "co");

    // Usados para filtro y sorting
    String LIST_SEPARATOR = "@@";
    String FIELD_SEPARATOR = "##";

    int FIRST_ELEMENT = 0;

}
