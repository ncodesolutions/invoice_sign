/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ncode.invoice.ws.cert.exceptions;

/**
 *

 */
public class CRLFileTamperedException extends CertificateCRLException {

    public CRLFileTamperedException() {
        super();
    }

    public CRLFileTamperedException(String msg) {
        super(msg);
    }

    public CRLFileTamperedException(Throwable th) {
        super(th);
    }

    public CRLFileTamperedException(String msg, Throwable th) {
        super(msg, th);
    }
}
