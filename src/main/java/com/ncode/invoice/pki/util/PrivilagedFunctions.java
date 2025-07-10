/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ncode.invoice.pki.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.security.AccessController;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivilegedAction;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

/**
 *

 */
public class PrivilagedFunctions {

    private static SSLSocketFactory getTrustedSSLSocketFactory() throws NoSuchAlgorithmException, KeyManagementException {
        SSLContext sslCont = SSLContext.getInstance("TLS");
        sslCont.init(null, new TrustManager[]{new AllTrustedManager()}, null);
        return sslCont.getSocketFactory();
    }

    public static File createTempFile(final String fileName) throws IOException {
        final StringBuffer sb = new StringBuffer();
        File fout = (File) AccessController.doPrivileged(new PrivilegedAction() {

            public Object run() {
                File _fout = null;
                try {
                    String dir = System.getProperty("java.io.tmpdir");
                    _fout = new File(dir, fileName);
                    if (_fout != null && !_fout.exists()) {
                        _fout.createNewFile();
                    }
                    //_fout = File.createTempFile(fileNamePrefix, fileNameSuffix);
                } catch (Exception ex) {
                    sb.append(ex);
                    _fout = null;
                }
                return _fout;
            }
        });
        if (fout == null) {
            throw new IOException(sb.toString());
        }
        return fout;
    }

    public static FileInputStream getFileInputStream(final String filePath) throws IOException {
        final StringBuffer sb = null;
        FileInputStream fin = (FileInputStream) AccessController.doPrivileged(new PrivilegedAction() {

            public Object run() {
                FileInputStream _fin = null;
                try {
                    _fin = new FileInputStream(filePath);
                } catch (Exception ex) {
                    sb.append(ex);
                    _fin = null;
                }
                return _fin;
            }
        });
        if (fin == null) {
            throw new IOException(sb.toString());
        }
        return fin;
    }

    public static FileOutputStream getFileOutputStream(final String filePath) throws IOException {
        final StringBuffer sb = null;
        FileOutputStream fout = (FileOutputStream) AccessController.doPrivileged(new PrivilegedAction() {

            public Object run() {
                FileOutputStream _fout = null;
                try {
                    _fout = new FileOutputStream(filePath);
                } catch (Exception ex) {
                    sb.append(ex);
                    _fout = null;
                }
                return _fout;
            }
        });
        if (fout == null) {
            throw new IOException(sb.toString());
        }
        return fout;
    }

    public static InputStream getInputStreamFromURL(final String url) throws IOException {
        final StringBuffer sb = null;
        InputStream fin = (InputStream) AccessController.doPrivileged(new PrivilegedAction() {

            public Object run() {
                URL u = null;
                URLConnection uc = null;
                InputStream _fin = null;
                try {
                    HttpsURLConnection.setDefaultSSLSocketFactory(getTrustedSSLSocketFactory());
                    u = new URL(url);
                    uc = u.openConnection();
                    uc.connect();
                    _fin = uc.getInputStream();
                } catch (Exception ex) {
                    sb.append(ex.getMessage());
                    _fin = null;
                }
                return _fin;
            }
        });
        if (fin == null) {
            throw new IOException(sb.toString());
        }
        return fin;
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
