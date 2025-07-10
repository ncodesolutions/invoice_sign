/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ncode.invoice.chain.exceptions;

public class ChainElemenValidityException extends ChainVerificationException {

    public ChainElemenValidityException() {
        this("Atlease one of the Certificate in chain is expired");
    }

    public ChainElemenValidityException(String msg) {
        super(msg);
    }
}
