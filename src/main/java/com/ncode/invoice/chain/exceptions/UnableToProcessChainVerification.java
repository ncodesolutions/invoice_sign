package com.ncode.invoice.chain.exceptions;

public class UnableToProcessChainVerification extends ChainVerificationException {

    public UnableToProcessChainVerification() {
        this("Unable to process chain verification.");
    }

    public UnableToProcessChainVerification(String msg) {
        super(msg);
    }

    public UnableToProcessChainVerification(Throwable ex) {
        super(ex);
    }
}
