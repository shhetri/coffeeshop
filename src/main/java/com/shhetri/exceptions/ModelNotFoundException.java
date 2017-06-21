package com.shhetri.exceptions;

public class ModelNotFoundException extends Exception {
    private static String MESSAGE = "The requested %s with %s: %s was not found";

    public ModelNotFoundException(String clazz, String key, Object value) {
        super(String.format(MESSAGE, clazz, key, value.toString()));
    }

    public ModelNotFoundException(String clazz, Object value) {
        super(String.format(MESSAGE, clazz, "id", value.toString()));
    }
}
