/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ncode.invoice.pki.util;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.KeyFactory;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.UnrecoverableKeyException;
import java.security.cert.Certificate;
import java.security.cert.CertificateEncodingException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateExpiredException;
import java.security.cert.CertificateFactory;
import java.security.cert.CertificateNotYetValidException;
import java.security.cert.X509Certificate;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *

 */
public class FileSigningServices {

    private static final Logger logger = LogManager.getLogger(FileSigningServices.class);

    protected static boolean debugAllow = true;
    protected static int errorCode = 200;

    protected final static void debug(Object obj) {
        if (debugAllow) {
            System.out.println("[" + new Date() + "]\t" + obj);
            // System.err.print(obj);
        }
    }

    protected static boolean isFileExists(String fileName) {
        File file = new File(fileName);
        return file.exists();
    }

    public static KeyStore loadKeyStore(String storeType, String storeFile, String storePass) {
        FileInputStream fin = null;
        KeyStore ks = null;
        try {
            fin = new FileInputStream(storeFile);
            ks = KeyStore.getInstance(storeType);
            ks.load(fin, storePass.toCharArray());
            return ks;
        } catch (FileNotFoundException fne) {
            logger.info("ERROR", fne);
            errorCode = ERROR_BAD_KEYSTORE_FILE;
        } catch (IOException ioe) {
            logger.info("ERROR", ioe);
            errorCode = ERROR_LOADING_KEYSTORE_FILE;
        } catch (KeyStoreException fne) {
            logger.info("ERROR", fne);
            errorCode = ERROR_BAD_KEYSTORE_TYPE;
        } catch (Exception fne) {
            logger.info("ERROR", fne);
            errorCode = ERROR_LOADING_KEYSTORE;
        } finally {
            try {
                if (fin != null) {
                    fin.close();
                }
            } catch (Exception ex) {
                logger.info("ERROR", ex);
            }
        }
        return null;
    }

    public static PrivateKey getPrivateKey(KeyStore ks, String alias, String password) {
        PrivateKey key = null;
        try {
            if (!ks.containsAlias(alias)) {
                errorCode = ERROR_BAD_KEY_ALIAS;
                return null;
            }
            if (password == null || password.trim().length() == 0) {
                errorCode = ERROR_BAD_KEY_PASSWORD;
                return null;
            }
            key = (PrivateKey) ks.getKey(alias, password.toCharArray());
        } catch (UnrecoverableKeyException ex) {
            logger.info("ERROR", ex);
            errorCode = ERROR_LOADING_PRIVATE_KEY;
        } catch (Exception ex) {
            logger.info("ERROR", ex);
            errorCode = ERROR_LOADING_KEYSTORE;
        }
        return key;
    }

    public static boolean validateCert(X509Certificate cert) {
        boolean revocked = false;
        try {
            cert.checkValidity();
            return true;
//            CRLValidator validator = CRLValidator.getInstance(cert);
//            revocked = validator.isReavocked();
//            if (revocked) {
//                errorCode = ERROR_CERT_REVOCKED;
//                return false;
//            } else {
//                return true;
//            }
        } catch (CertificateExpiredException ex) {
            logger.info("ERROR", ex);
            errorCode = ERROR_CERT_EXPIRED;
            return false;
        } catch (CertificateNotYetValidException ex) {
            logger.info("ERROR", ex);
            errorCode = ERROR_CERT_EXPIRED;
            return false;
        } catch (Exception ex) {
            logger.info("ERROR", ex);
            return true;
        }
    }

    public static File[] listFiles(String directoryName) {
        File directory = new File(directoryName);
        File[] fList = directory.listFiles();
        return fList;
    }

    // list files with sub folder
    public static List<File> listAllFiles(String path) {
        File dir = new File(path);
        String[] extensions = new String[]{"pdf", "PDF"};
        List<File> files = (List<File>) FileUtils.listFiles(dir, extensions, true);

        return files;
    }

    protected static boolean validateCertSerial(X509Certificate cert, String serialNumber) {
        try {

            Map certParam = CertVerificationService.getCertParams(cert);
            String clientCertSrNo = (String) certParam.get(CertVerificationService.CERT_PARAM_SRNO);
            System.out.println(" matching serial numer ==>" + clientCertSrNo + "==> to ==>" + serialNumber);
            if (clientCertSrNo.trim().equalsIgnoreCase(serialNumber.trim())) {
                System.out.println(" serial number matched");
                return true;
            } else {
                System.out.println(" serial number DID NOT matched");
                return false;
            }
        } catch (Exception ex) {
            logger.info("ERROR", ex);
            return true;
        }
    }

    protected static boolean validateCertSerial12(X509Certificate cert) {
        try {
            List certList = new ArrayList();
            certList.add("53 3e b2 0e");
            certList.add("53 3e b1 72");

            Map certParam = CertVerificationService.getCertParams(cert);
            String clientCertSrNo = (String) certParam.get(CertVerificationService.CERT_PARAM_SRNO);
            if (certList.contains(clientCertSrNo)) {
//                System.out.println(" serial number matched");
                return true;
            } else {
//                System.out.println(" serial number DID NOT matched");
                return false;
            }
        } catch (Exception ex) {
            logger.info("ERROR", ex);
            return true;
        }
    }

    public static Certificate[] getCertificateChain(KeyStore ks, String alias) {
        Certificate[] chain = null;
        try {
            if (!ks.containsAlias(alias)) {
                errorCode = ERROR_BAD_KEY_ALIAS;
                return null;
            }
            chain = ks.getCertificateChain(alias);
        } catch (Exception ex) {
            logger.info("ERROR", ex);
            errorCode = ERROR_LOADING_CERT_CHAIN;
        }
        return chain;
    }

    public static String getFirstAlias(KeyStore ks) throws KeyStoreException {
        String alias = null;
        Enumeration en = ks.aliases();
        while (en.hasMoreElements()) {
            alias = (String) en.nextElement();
            break;
        }
        return alias;
    }

    public static String[] getAliasList(KeyStore ks, int size) throws KeyStoreException {

        String[] alias = new String[size];
        int i = 0;
        Enumeration en = ks.aliases();
        while (en.hasMoreElements()) {
            alias[i] = (String) en.nextElement();
//            break;
            i++;
        }
        return alias;
    }

    protected static X509Certificate getCertificate(KeyStore ks, String alias) {
        X509Certificate cert = null;
        try {
            if (!ks.containsAlias(alias)) {
                errorCode = ERROR_BAD_KEY_ALIAS;
                return null;
            }
            cert = (X509Certificate) ks.getCertificate(alias);
        } catch (Exception ex) {
            logger.info("ERROR", ex);
            errorCode = ERROR_LOADING_CERT_CHAIN;
        }
        return cert;
    }

    public static X509Certificate loadCertificateFromBase64(String base64Cert) throws CertificateException, IOException {
        byte[] bytes = null;
        bytes = Base64Service.decode(base64Cert);
        return loadCertificateFromBytes(bytes);
    }

    public static X509Certificate loadCertificateFromBytes(byte[] bytes) throws CertificateException {
        ByteArrayInputStream ins = new ByteArrayInputStream(bytes);
        CertificateFactory cf = CertificateFactory.getInstance("X509");
        return (X509Certificate) cf.generateCertificate(ins);
    }

    public static PublicKey loadPublicKeyFromBase64(String pubKey) throws InvalidKeySpecException, NoSuchAlgorithmException, IOException {
        PublicKey serverPubKey = null;
        X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(Base64Service.decode(pubKey));
        KeyFactory keyFact = KeyFactory.getInstance("RSA");
        serverPubKey = keyFact.generatePublic(x509KeySpec);
        return serverPubKey;
    }

    public static PrivateKey loadPrivateKeyFromBase64(String prvKeyStr) throws IOException, NoSuchAlgorithmException, InvalidKeySpecException {
        PrivateKey prvKey = null;
        X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(Base64Service.decode(prvKeyStr));
        KeyFactory keyFact = KeyFactory.getInstance("RSA");
        prvKey = keyFact.generatePrivate(x509KeySpec);
        return prvKey;
    }

//    protected static boolean testCompoent(X509Certificate cert) throws NoSuchAlgorithmException, CertificateEncodingException, UnsupportedEncodingException {
//        System.out.println("Certificate serial number validation");
//
//        Map certParam = CertVerificationService.getCertParams(cert);
//        String serialno = (String) certParam.get(CertVerificationService.CERT_PARAM_SRNO);
//
//        if ("‎4d bf 45 ed".trim().equalsIgnoreCase(serialno)) { //‎
//            return true;
//        }
//        else if("19 ea 73 a2 01 00 03 1a".trim().equalsIgnoreCase(serialno)) { // test certificate
//            return true;
//        }
//        else if("‎4d be d2 bc".trim().equalsIgnoreCase(serialno)) { // test certificate
//            return true;
//        }
//        else if("‎4d af 22 24".trim().equalsIgnoreCase(serialno)) { // test certificate
//            return true;
//        }
//        else {
//            errorCode = ERROR_INVALID_CERT;
//            return false;
//        }
//        // added by USSHAH for the delivery @ SBI delhi tranco and icegate custome END
//    }
    protected static boolean testCompoent(X509Certificate cert) throws NoSuchAlgorithmException, CertificateEncodingException, UnsupportedEncodingException {
        System.out.println("Certificate Validated");

        String serialno = Base64Service.convertToString(cert.getSerialNumber().toByteArray(), 0);
        List<String> l = new ArrayList();
        l.add(" 53 17 44 52".trim()); // client live certificate
        l.add(" ‎‎‎‎‎53 17 44 52".trim()); // client live certificate
        l.add("‎‎53 17 44 52 ".trim()); // client live certificate
        l.add(" ‎53 17 44 52 ".trim()); // client live certificate
//        l.add(" 4d be d2 bc".trim());// test certificate
//        l.add(" 19 ea 73 a2 01 00 03 1a".trim());// test certificate

        if (l.contains(serialno.trim())) {
            return true;
        } else {
            return false;
        }
    }
    public final static int SUCCESSFULL = 200;
    public final static int FAILURE = 220;
    public final static int ERROR_SOURCE_FILE_NOT_FOUND = 401;
    public final static int ERROR_SOURCE_FILE_IO_ERROR = 402;
    public final static int ERROR_LOADING_KEYSTORE = 411;
    public final static int ERROR_LOADING_KEYSTORE_FILE = 412;
    public final static int ERROR_BAD_KEYSTORE_TYPE = 413;
    public final static int ERROR_BAD_KEYSTORE_FILE = 414;
    public final static int ERROR_BAD_KEYSTORE_PASSWORD = 415;
    public final static int ERROR_BAD_KEY_ALIAS = 416;
    public final static int ERROR_BAD_KEY_PASSWORD = 417;
    public final static int ERROR_LOADING_PRIVATE_KEY = 418;
    public final static int ERROR_LOADING_CERT_CHAIN = 419;
    public final static int ERROR_CERT_EXPIRED = 420;
    public final static int ERROR_CERT_REVOCKED = 421;
    public final static int ERROR_WRITING_DEST_FILE = 431;
    public final static int ERROR_WRITE_PERMISSION_DEST_FILE = 432;
    public final static int ERROR_FILE_SIGNING = 501;
    public final static int ERROR_UNKNOWN = 502;
    public final static int ERROR_COMPONENT_EXPIRED = 503;
    public final static int ERROR_INVALID_USAGE = 504;
    public final static int ERROR_INVALID_KEY_SUPPLIED = 505;
    public final static int ERROR_INVALID_TOKEN_PASSWORD_SUPPLIED = 506;
    public final static int ERROR_INSTALL_TOKEN_DRIVERS = 507;
    public final static int ERROR_INVALID_CERT = 508;
    public final static int ERROR_MATCHING_CERT_SERIAL_NUMBER = 509;
}
