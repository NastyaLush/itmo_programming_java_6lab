package test.laba.common.exception;


import test.laba.common.IO.Console;

public class VariableException extends Exception {
    String message;
    public VariableException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
