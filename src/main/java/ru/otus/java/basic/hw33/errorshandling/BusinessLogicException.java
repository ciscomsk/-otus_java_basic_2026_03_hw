package ru.otus.java.basic.hw33.errorshandling;

public class BusinessLogicException extends RuntimeException {
    private String code;
    private String description;

    public BusinessLogicException(String code, String description) {
        this.code = code;
        this.description = description;
    }

    public String getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }
}
