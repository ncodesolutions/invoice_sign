package com.ncode.invoice.pki.crl;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.cert.CertStoreException;
import java.security.cert.X509CRL;
import java.security.cert.X509Certificate;

public abstract class CRLValidator {

    private String url;
    private String startsWithURL;
    private X509CRL[] crlObj;
    protected X509Certificate cert;

    protected CRLValidator(X509Certificate cert) throws NullPointerException, IOException, CertStoreException {
        this.cert = cert;
        this.startsWithURL = bindWithProtocol();
        String tmpurl = getCRLPath(this.cert);
        setURL(tmpurl);
    }

    protected abstract String bindWithProtocol();

    protected abstract X509CRL[] loadCRLObject(String url) throws IOException, CertStoreException;

    public static CRLValidator getInstance(X509Certificate cert) throws UnsupportedEncodingException, NullPointerException, IOException, CertStoreException {
        String crl = getCRLPath(cert);
        if (crl == null || crl.trim().length() == 0) {
            throw new NullPointerException("CRL location cannot be null.");
        }
        if (crl.toLowerCase().startsWith("ldap://")) {
            return new LDAPCRLValidator(cert);
        } else {
            return new HTTPCRLValidator(cert);
        }
    }

    private static String getCRLPath(X509Certificate cert) throws UnsupportedEncodingException {
        int crlStart = 0;
        String crl = new String(cert.getExtensionValue("2.5.29.31"), "UTF-8");
        if (crl != null && crl.trim().length() > 0) {
            crlStart = crl.indexOf("http");
            if (crlStart < 0) {
                crlStart = crl.indexOf("ldap");
            }
            crl = crl.substring(crlStart);
        }
        return crl;
    }

    private void setURL(String url) throws NullPointerException, IOException, CertStoreException {
        if (this.url == null || !url.toLowerCase().endsWith(this.url)) {
            if (url.toLowerCase().startsWith("ldap://")) {
                this.url = url.substring(startsWithURL.length(), url.length());
            } else {
                this.url = url;
            }
            this.crlObj = loadCRLObject(this.url);
        }
    }

    public boolean isReavocked() {
        boolean isReavocked = false;
        if (this.crlObj != null && this.crlObj.length > 0) {
            for (int lp = 0; lp < crlObj.length; lp++) {
                isReavocked = crlObj[lp].isRevoked(this.cert);
                if (isReavocked) {
                    break;
                }
            }
        }
        return isReavocked;

    }
}
