/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ncode.invoice.pki.crl;

import java.io.IOException;
import java.security.cert.CertStore;
import java.security.cert.CertStoreException;
import java.security.cert.LDAPCertStoreParameters;
import java.security.cert.X509CRL;
import java.security.cert.X509CRLSelector;
import java.security.cert.X509Certificate;
import java.util.Collection;
import java.util.Iterator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *

 */
class LDAPCRLValidator extends CRLValidator {

    private static final Logger logger = LogManager.getLogger(LDAPCRLValidator.class);

    public LDAPCRLValidator(X509Certificate cert) throws NullPointerException, IOException, CertStoreException {
        super(cert);
    }

    private CertStore getLdapCertStore(String ldapUrl) {
        CertStore cs = null;
        LDAPCertStoreParameters params = new LDAPCertStoreParameters(ldapUrl);
        try {
            cs = CertStore.getInstance("LDAP", params);
        } catch (Exception ex) {
            logger.info("Error", ex);
        }
        return cs;
    }

    private X509CRL[] getCrls(CertStore cs, X509CRLSelector crlSel) throws CertStoreException {
        Collection col = null;
        X509CRL[] crls = null;
        col = cs.getCRLs(crlSel);
        Iterator it = null;
        if (col != null && !col.isEmpty() && col.size() > 0) {
            it = col.iterator();
            crls = new X509CRL[col.size()];
            int count = 0;
            while (it.hasNext()) {
                crls[count] = (X509CRL) it.next();
            }
        }
        return crls;
    }

    private X509CRL[] searchCRL(CertStore cs, String issuerName) throws IOException, CertStoreException {
        X509CRLSelector crlSel = new X509CRLSelector();
        crlSel.addIssuerName(issuerName);
        return getCrls(cs, crlSel);

    }

    private X509CRL[] searchCRL(CertStore cs, X509Certificate cert) throws CertStoreException {
        X509CRLSelector crlSel = new X509CRLSelector();
        crlSel.setCertificateChecking(cert);
        return getCrls(cs, crlSel);
    }

    protected X509CRL[] loadCRLObject(String url) throws IOException, CertStoreException {
        X509CRL crl[] = null;
        CertStore cs = getLdapCertStore(url);
        if (cs == null) {
            throw new IOException("LDAP Cert Store Not loaded.");
        }
        return searchCRL(cs, this.cert);

    }

    protected String bindWithProtocol() {
        return "ldap://";
    }

}
