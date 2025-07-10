/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ncode.invoice.ws.cert.exceptions;

import java.security.cert.CertificateException;

/**
 *

 */
public class CertificateCRLException extends CertificateException {

    public CertificateCRLException() {
        super();
    }

    public CertificateCRLException(String msg) {
        super(msg);
    }

    public CertificateCRLException(Throwable th) {
        super(th);
    }

    public CertificateCRLException(String msg, Throwable th) {
        super(msg, th);
    }
}
