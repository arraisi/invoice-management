package com.arraisi.invoice.exception;

// checked exception
public class VirtualAccountNotFoundException extends Exception {
    public VirtualAccountNotFoundException() {
    }

    public VirtualAccountNotFoundException(String message) {
        super(message);
    }
}
