package com.ncode.invoice.chain.exceptions;

public class ChainVerificationException extends Exception {

    public ChainVerificationException() {
        this("Chain verification failed.");
    }

    public ChainVerificationException(String msg) {
        super(msg);
    }

    public ChainVerificationException(Throwable ex) {
        super(ex);
    }
}
