package com.mercadolibre.products.util;

public class AppConstant {

    public static boolean DEBUG = true;
    public static final String BASE_URL = "https://api.mercadolibre.com";
    public static final int PAGE_LIMIT = 20;
    public static final int DEFAULT_OFFSET = 0;

    public static boolean isNumeric(String cadena) {

        boolean resultado;

        try {
            Integer.parseInt(cadena);
            resultado = true;
        } catch (NumberFormatException excepcion) {
            resultado = false;
        }

        return resultado;
    }

}
