/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ncode.invoice.pki.crl;

import com.ncode.invoice.pki.util.PrivilagedFunctions;
import java.io.IOException;
import java.io.InputStream;
import java.security.cert.CertStoreException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509CRL;
import java.security.cert.X509Certificate;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

class HTTPCRLValidator extends CRLValidator {

    private static final Logger logger = LogManager.getLogger(HTTPCRLValidator.class);

    public HTTPCRLValidator(X509Certificate cert) throws NullPointerException, IOException, CertStoreException {
        super(cert);
    }

    protected X509CRL[] loadCRLObject(String url) throws IOException {
        InputStream uin = null;
        X509CRL crl[] = new X509CRL[1];
        try {
            uin = PrivilagedFunctions.getInputStreamFromURL(url);
            crl[0] = (X509CRL) CertificateFactory.getInstance("X509").generateCRL(uin);
            uin.close();
        } catch (Exception ex) {
            logger.info("Error", ex);
            throw new IOException("Unable to download CRL from location '" + url + "'");
        }
        return crl;
    }

    protected String bindWithProtocol() {
        return "http://";
    }

}
