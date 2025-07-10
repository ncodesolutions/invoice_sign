/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ncode.invoice.ws.cert.exceptions;

/**
 *

 */
public class CRLNotUpdatedException extends CertificateCRLException {

    public CRLNotUpdatedException() {
        super();
    }

    public CRLNotUpdatedException(String msg) {
        super(msg);
    }

    public CRLNotUpdatedException(Throwable th) {
        super(th);
    }

    public CRLNotUpdatedException(String msg, Throwable th) {
        super(msg, th);
    }
}
