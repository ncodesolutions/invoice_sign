package com.ncode.invoice.chain.exceptions;

public class NoFullChainException extends ChainVerificationException {

    public NoFullChainException() {
        this("Full Certificate chain does not exists.");
    }

    public NoFullChainException(String msg) {
        super(msg);
    }
}
