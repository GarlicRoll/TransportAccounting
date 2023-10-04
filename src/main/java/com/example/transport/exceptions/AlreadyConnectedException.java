package com.example.transport.exceptions;

import java.rmi.AlreadyBoundException;

public class AlreadyConnectedException extends AlreadyBoundException {
    public AlreadyConnectedException() {
        super();
    }

    public AlreadyConnectedException(String message) {
        super(message);
    }
}
