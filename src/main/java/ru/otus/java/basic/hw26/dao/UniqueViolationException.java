package ru.otus.java.basic.hw26.dao;

public class UniqueViolationException extends RuntimeException {
    public UniqueViolationException(String message) {
        super(message);
    }
}
