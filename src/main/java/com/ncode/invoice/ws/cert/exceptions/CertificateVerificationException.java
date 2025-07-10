/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ncode.invoice.ws.cert.exceptions;

import java.security.cert.CertificateException;

/**
 *

 */
public class CertificateVerificationException extends CertificateException {

    public CertificateVerificationException() {
        super();
    }

    public CertificateVerificationException(String msg) {
        super(msg);
    }

    public CertificateVerificationException(Throwable th) {
        super(th);
    }

    public CertificateVerificationException(String msg, Throwable th) {
        super(msg, th);
    }
}
