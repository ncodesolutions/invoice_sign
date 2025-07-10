/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ncode.invoice.pki.util;

import com.ncode.invoice.ws.cert.exceptions.CRLDownloadingException;
import com.ncode.invoice.ws.cert.exceptions.CertificateCRLException;
import com.ncode.invoice.ws.cert.exceptions.CertificateRevokedException;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.cert.CertStore;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.LDAPCertStoreParameters;
import java.security.cert.X509CRL;
import java.security.cert.X509CRLSelector;
import java.security.cert.X509Certificate;
import java.util.Collection;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *

 */
public class CRLService {

    private static final Logger logger = LogManager.getLogger(CRLService.class);

    private static String CLASSNAME = "CRLService";

    private static SSLSocketFactory getTrustedSSLSocketFactory() throws NoSuchAlgorithmException, KeyManagementException {
        SSLContext sslCont = SSLContext.getInstance("TLS");
        sslCont.init(null, new TrustManager[]{new AllTrustedManager()}, null);
        return sslCont.getSocketFactory();
    }

    public static File downloadCRLFile(String url, String toFile) throws CRLDownloadingException {

        String exPrefix = "'" + CLASSNAME + ":downloadCRLFile(url,toFile)'>>";
        InputStream uin = null;
        byte data[] = null;
        FileOutputStream fout = null;
        File toFileObj = null;
        URL u = null;
        URLConnection uc = null;
        try {
            HttpsURLConnection.setDefaultSSLSocketFactory(getTrustedSSLSocketFactory());
            u = new URL(url);
            uc = u.openConnection();
            uc.connect();
            uin = uc.getInputStream();
            X509CRL crl = (X509CRL) CertificateFactory.getInstance("X509").generateCRL(uin);
            data = crl.getEncoded();
            toFileObj = new File(toFile);
            if (toFileObj.exists()) {
                toFileObj.delete();
            }
            toFileObj.createNewFile();
            fout = new FileOutputStream(toFileObj);
            fout.write(data);
            uin.close();
            fout.flush();
            fout.close();
        } catch (Exception ex) {
            logger.info("Error", ex);
            throw new CRLDownloadingException(exPrefix + "Unable to download CRL from location '" + url + "' store to cached directory '" + toFile + "'");
        }
        return toFileObj;
    }

    public static X509CRL getX509CRLfromURL(String url) throws IOException {
        String exPrefix = "'" + CLASSNAME + ":downloadCRLFile(url,toFile)'>>";
        InputStream uin = null;
//            byte data[]=null;
//            URL u = null;
//            URLConnection uc = null;
        X509CRL crl = null;
        try {
//                HttpsURLConnection.setDefaultSSLSocketFactory(getTrustedSSLSocketFactory());
//                u = new URL(url);
//                uc = u.openConnection();
//                uc.connect();
//                uin = uc.getInputStream();
            uin = PrivilagedFunctions.getInputStreamFromURL(url);
            crl = (X509CRL) CertificateFactory.getInstance("X509").generateCRL(uin);
            uin.close();
        } catch (Exception ex) {
            logger.info("Error", ex);
            throw new IOException(exPrefix + "Unable to download CRL from location '" + url + "'");
        }
        return crl;
    }

    public static X509CRL getX509CRLfromInputStream(InputStream uin) throws IOException {
        String exPrefix = "'" + CLASSNAME + ":getX509CRLfromInputStream(InputStream)'>>";
        X509CRL crl = null;
        try {
            crl = (X509CRL) CertificateFactory.getInstance("X509").generateCRL(uin);
            uin.close();
        } catch (Exception ex) {
            logger.info("Error", ex);
            throw new IOException(exPrefix + "Unable to download CRL from Stream. '");
        }
        return crl;
    }

    public static boolean validateCertWithCRL(X509Certificate cert, X509CRL crlObj) throws CertificateRevokedException {
        String exPrefix = "'" + CLASSNAME + ":validateCertWithCRL(X509Certificate cert,X509CRL crlObj)'>>";
        if (crlObj.isRevoked(cert)) {
            throw new CertificateRevokedException(exPrefix + "Certificate is reviked.");
        }
        return true;
    }

    public static boolean validateCertWithLDAPCRL(X509Certificate cert, String localFileName, String url, PublicKey issuerPubKey) throws CertificateCRLException, CertificateRevokedException {
        String exPrefix = "'" + CLASSNAME + ":validateCertWithLDAPCRL(X509Certificate cert,String localFileName,String url,PublicKey issuerPubKey)'>>";
        Collection crlList = null;
        X509CRL crlObj = null;
        LDAPCertStoreParameters lcsp = new LDAPCertStoreParameters("", 359);
        X509CRLSelector crlSel = new X509CRLSelector();
        try {
            CertStore cs = CertStore.getInstance("LDAP", lcsp);
            crlList = cs.getCRLs(crlSel);
        } catch (Exception ex) {
            logger.info("Error", ex);
            throw new CertificateCRLException(ex);
        }
        if (crlList == null || crlList.size() == 0) {
            return true;
        } else {
            throw new CertificateRevokedException(exPrefix + "Certificate is reviked.");
        }
    }

    private static class AllTrustedManager implements X509TrustManager {

        public void checkClientTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {

        }

        public void checkServerTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {

        }

        public X509Certificate[] getAcceptedIssuers() {
            return new X509Certificate[0];
        }

    }

}
