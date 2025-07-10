/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ncode.invoice.ws.cert.exceptions;

/**
 *

 */
public class CRLDownloadingException extends CertificateCRLException {

    public CRLDownloadingException() {
        super();
    }

    public CRLDownloadingException(String msg) {
        super(msg);
    }

    public CRLDownloadingException(Throwable th) {
        super(th);
    }

    public CRLDownloadingException(String msg, Throwable th) {
        super(msg, th);
    }
}
