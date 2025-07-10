/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ncode.invoice.ws.cert.exceptions;

/**
 *

 */
public class CertificateRevokedException extends CertificateCRLException {

    public CertificateRevokedException() {
        super();
    }

    public CertificateRevokedException(String msg) {
        super(msg);
    }

    public CertificateRevokedException(Throwable th) {
        super(th);
    }

    public CertificateRevokedException(String msg, Throwable th) {
        super(msg, th);
    }
}
