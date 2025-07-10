package com.ncode.invoice.chain.exceptions;

public class ChainElementRevokedException extends ChainVerificationException {

    public ChainElementRevokedException() {
        this("Atlease one of the certificate in chain is revoked.");
    }

    public ChainElementRevokedException(String msg) {
        super(msg);
    }
}
