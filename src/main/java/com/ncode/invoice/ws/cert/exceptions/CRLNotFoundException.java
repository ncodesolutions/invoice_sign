/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ncode.invoice.ws.cert.exceptions;

/**
 *

 */
public class CRLNotFoundException extends CertificateCRLException {

    public CRLNotFoundException() {
        super();
    }

    public CRLNotFoundException(String msg) {
        super(msg);
    }

    public CRLNotFoundException(Throwable th) {
        super(th);
    }

    public CRLNotFoundException(String msg, Throwable th) {
        super(msg, th);
    }
}
