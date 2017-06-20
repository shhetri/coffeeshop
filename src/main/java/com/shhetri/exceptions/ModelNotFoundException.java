package com.shhetri.exceptions;

public class ModelNotFoundException extends Exception {
    private static String MESSAGE = "The requested %s with id %d was not found";

    public ModelNotFoundException(String clazz, int id) {
        super(String.format(MESSAGE, clazz, id));
    }
}
