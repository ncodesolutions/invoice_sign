package com.ncode.invoice.chain.exceptions;

public class InvalidRootException extends ChainVerificationException {

    public InvalidRootException() {
        this("No valid root certificate found in chain.");
    }

    public InvalidRootException(String msg) {
        super(msg);
    }
}
