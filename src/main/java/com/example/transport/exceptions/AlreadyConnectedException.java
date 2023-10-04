package com.example.transport.exceptions;

public class AlreadyConnectedException extends RuntimeException {
    public AlreadyConnectedException() {
        super();
    }

    public AlreadyConnectedException(String message) {
        super(message);
    }
}
