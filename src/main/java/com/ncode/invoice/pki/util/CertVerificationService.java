/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ncode.invoice.pki.util;

import com.ncode.invoice.chain.exceptions.ChainVerificationException;
import com.ncode.invoice.chain.exceptions.UnableToProcessChainVerification;
import com.ncode.invoice.pki.crl.CRLValidator;
import com.ncode.invoice.pkiconf.parser.CAParamBean;
import com.ncode.invoice.pkiconf.parser.PKIConfParser;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.cert.CertStoreException;
import java.security.cert.Certificate;
import java.security.cert.CertificateEncodingException;
import java.security.cert.CertificateExpiredException;
import java.security.cert.CertificateFactory;
import java.security.cert.CertificateNotYetValidException;
import java.security.cert.X509CRL;
import java.security.cert.X509CRLEntry;
import java.security.cert.X509Certificate;
import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *

 */
public class CertVerificationService {

    private static final Logger logger = LogManager.getLogger(CertVerificationService.class);

    public static final String CERT_PARAM_AKI = "aki";
    public static final String CERT_PARAM_SKI = "ski";
    public static final String CERT_PARAM_CERTCLASS = "certClass";
    public static final String CERT_PARAM_CERTCLASSTYPE = "certClassType";
    public static final String CERT_PARAM_EXPDATE = "expDate";
    public static final String CERT_PARAM_SRNO = "srNo";
    public static final String CERT_PARAM_THUMBPRINT = "thumbPrint";
    public static final String CERT_PARAM_PUBLICKEY = "publicKey";
    public static final String CERT_PARAM_CRL = "crl";
    public static final String CERT_PARAM_OU = "OUstr";
    public static final String CERT_USAGE_DGSIGN = "DGSIG";
    public static final String CERT_USAGE_NONREP = "NONREP";
    public static final String CERT_USAGE_KEYENC = "KEYENC";
    public static final String CERT_USAGE_DATAENC = "DATAENC";
    public static final String CERT_USAGE_DOCSIG = "DOCSIG";
    public static final String CERT_USAGE_AUTH = "AUTH";
    public static final String CERT_SUBJECTDN_MAP = "SubDNMap";
    public static final String CERT_ISSUERDN_MAP = "IssuerDNMap";
    private static PKIConfParser parser = new PKIConfParser();

    public static Map getCertParams(X509Certificate cert) throws NoSuchAlgorithmException, CertificateEncodingException, UnsupportedEncodingException {
        String aki = null;
        String ski = null;
        String certClass = null;
        String certClassType = null;
        Date expDate = null;
        String srNo = null;
        String thumbPrint = null;
        //PrivateKey privateKey=null;
        PublicKey publicKey = null;
        String crl = null;
        boolean[] keyUsage = null;
        String OUstr = null;//cert.getSubjectDN().toString();
        int crlStart = 0;
        Map certParamMap = new HashMap();

        OUstr = cert.getSubjectDN().toString() + cert.getIssuerDN().toString();
        byte[] cp = cert.getExtensionValue("2.5.29.32");
        if (cp != null) {
            OUstr = removeNonUtf8CompliantCharacters(new String(cp, "UTF-8") + OUstr);
        }
        OUstr = OUstr.toLowerCase();

        if (cert.getSubjectDN().toString().toLowerCase().trim().indexOf("personal") >= 0) {
            certClassType = "a"; // individual
        } else {
            certClassType = "b"; // Organization
        }
        if (cert.getExtensionValue("2.5.29.35") == null) {
            aki = "";
        } else {
            aki = Base64Service.convertToString(cert.getExtensionValue("2.5.29.35"), 6);
        }
        srNo = Base64Service.convertToString(cert.getSerialNumber().toByteArray(), 0);
        publicKey = cert.getPublicKey();
        expDate = cert.getNotAfter();

        MessageDigest thumbMesgDigest = MessageDigest.getInstance("SHA1");
        byte[] encoding = cert.getEncoded();
        byte[] digest = thumbMesgDigest.digest(encoding);
        thumbPrint = Base64Service.convertToString(digest, 0);

        if (cert.getExtensionValue("2.5.29.31") == null) {
            crl = "";
        } else {
            crl = new String(cert.getExtensionValue("2.5.29.31"), "UTF-8");
        }

        if (cert.getExtensionValue("2.5.29.14") == null) {
            ski = "";
        } else {
            ski = Base64Service.convertToString(cert.getExtensionValue("2.5.29.14"), 4).trim();
        }

        if (crl != null && crl.trim().length() > 0) {
            crlStart = crl.indexOf("http");
            if (crlStart >= 0) {
                crl = crl.substring(crlStart, crl.indexOf(".crl", crlStart) + 4);
            } else {
                crlStart = crl.indexOf("ldap");
                if (crlStart >= 0) {
                    crl = crl.substring(crlStart);
                } else {
                    crl = "";
                }
            }
        }

        if (OUstr.toLowerCase().indexOf("class 3a") >= 0 || OUstr.toLowerCase().indexOf("class iiia") >= 0) {
            certClass = "3a";
            certClassType = "a";
        } else if (OUstr.toLowerCase().indexOf("class 3b") >= 0 || OUstr.toLowerCase().indexOf("class iiib") >= 0) {
            certClass = "3b";
            certClassType = "b";
        } else if (OUstr.toLowerCase().indexOf("class 3") >= 0 || OUstr.toLowerCase().indexOf("class iii") >= 0) {
            certClass = "3";
        } else if (OUstr.toLowerCase().indexOf("class3") >= 0 || OUstr.toLowerCase().indexOf("classiii") >= 0) {
            certClass = "3";
        } else if (OUstr.toLowerCase().indexOf("class 2a") >= 0 || OUstr.toLowerCase().indexOf("class iia") >= 0) {
            certClass = "2a";
            certClassType = "a";
        } else if (OUstr.toLowerCase().indexOf("class 2b") >= 0 || OUstr.toLowerCase().indexOf("class iib") >= 0) {
            certClass = "2b";
            certClassType = "b";
        } else if (OUstr.toLowerCase().indexOf("class 2") >= 0 || OUstr.toLowerCase().indexOf("class ii") >= 0) {
            certClass = "2";

        } else if (OUstr.toLowerCase().indexOf("class 1") >= 0 || OUstr.toLowerCase().indexOf("class i") >= 0) {
            certClass = "1";
        } else {
            certClass = "";
        }

        keyUsage = cert.getKeyUsage();
        if (keyUsage != null) {
            certParamMap.put(CERT_USAGE_DGSIGN, Boolean.valueOf(keyUsage[0]));
            // System.out.println("0 : "+keyUsage[0]);
            certParamMap.put(CERT_USAGE_NONREP, Boolean.valueOf(keyUsage[1]));
            // System.out.println("0 : "+keyUsage[1]);
            certParamMap.put(CERT_USAGE_KEYENC, Boolean.valueOf(keyUsage[2]));
            //System.out.println("0 : "+keyUsage[2]);
            certParamMap.put(CERT_USAGE_DATAENC, Boolean.valueOf(keyUsage[3]));
            //System.out.println("0 : "+keyUsage[3]);
        }
        //1.3.6.1.5.5.7.3.1 SSL Server
        //1.3.6.1.5.5.7.3.2 Client Authentication
        //1.3.6.1.5.5.7.3.3 Code Signing
        //1.3.6.1.5.5.7.3.4 email signing encryption
        //1.3.6.1.5.5.7.3.7 IPSEC User cert
        //1.3.6.1.5.5.7.3.8 Time Stamping
        //1.3.6.1.5.5.7.3.9 OCSP
        //1.3.6.1.4.1.311.10.3.12 Doc Signing
        if (cert.getVersion() >= 3) {
            try {
                List exOid = cert.getExtendedKeyUsage();
                if (exOid != null) {
                    certParamMap.put(CERT_USAGE_AUTH, Boolean.valueOf(exOid.contains("1.3.6.1.5.5.7.3.2")));  //1.3.6.1.5.5.7.3.2 client Authentication
                    certParamMap.put(CERT_USAGE_DOCSIG, Boolean.valueOf(exOid.contains("1.3.6.1.4.1.311.10.3.12")));  //1.3.6.1.4.1.311.3.10.3.12 doc signing
                    //System.out.println("Extended Key usage : OK");
                }
            } catch (Exception ex1) {
                logger.info("ERROR", ex1);
                
            }
        }

        certParamMap.put(CERT_PARAM_AKI, aki.trim());
        certParamMap.put(CERT_PARAM_SKI, ski.trim());
        certParamMap.put(CERT_PARAM_CERTCLASS, certClass.trim());
        certParamMap.put(CERT_PARAM_CERTCLASSTYPE, certClassType.trim());
        certParamMap.put(CERT_PARAM_EXPDATE, expDate);
        certParamMap.put(CERT_PARAM_SRNO, srNo.trim());
        certParamMap.put(CERT_PARAM_THUMBPRINT, thumbPrint.trim());
        certParamMap.put(CERT_PARAM_PUBLICKEY, publicKey);
        certParamMap.put(CERT_PARAM_CRL, crl.trim());
        certParamMap.put(CERT_PARAM_OU, OUstr.trim());

        return certParamMap;
    }

    private static String removeNonUtf8CompliantCharacters(final String inString) {
        if (null == inString) {
            return null;
        }
        byte[] byteArr = inString.getBytes();
        ByteArrayOutputStream bout = new ByteArrayOutputStream();
        for (int i = 0; i < byteArr.length; i++) {
            byte ch = byteArr[i];
            if (((ch > 31 && ch < 253) || ch == '\t' || ch == '\n' || ch == '\r' || ch == ' ')) {
                bout.write(ch);
            }
        }
        return new String(bout.toByteArray());
    }

    public static boolean validateCertChain(String alias, KeyStore ks, String keyStorePaswd, String rootSki) throws KeyStoreException {
        Certificate[] cert = ks.getCertificateChain(alias);
        int totalCertificate = cert.length;
        X509Certificate ccaCert = (X509Certificate) cert[totalCertificate - 1];
        String ccaSKI = Base64Service.convertToString(ccaCert.getExtensionValue("2.5.29.14"), 4).trim();
        System.out.println("CCA AKI" + ccaSKI);
        return (rootSki.indexOf(ccaSKI) >= 0);
    }

    public static Map validateCertChainMap(KeyStore ks, String ksPassword, Map certMap, String rootSKI) throws KeyStoreException {
        String alias = null;
        Iterator iterator = certMap.keySet().iterator();

        while (iterator.hasNext()) {
            alias = (String) iterator.next();
            if (!validateCertChain(alias, ks, ksPassword, rootSKI)) {
                certMap.remove(alias);
            }
        }

        return certMap;
    }

    public static boolean validateCertDate(X509Certificate cert, Date date) {
        boolean isValid = true;
        try {
            cert.checkValidity(date);
        } catch (CertificateNotYetValidException ye) {
            logger.info("ERROR", ye);
            isValid = false;
        } catch (CertificateExpiredException ce) {
            logger.info("ERROR", ce);
            isValid = false;
        }
        return isValid;
    }

    public static boolean validateCertDate(X509Certificate cert, String serverDate) throws ParseException {
        Date date = new Date();
        //DateFormat df=new SimpleDateFormat("dd/MM/yyyy");
        //date=df.parse(serverDate);
        date = DateUtility.getDateFromString(serverDate);
        return validateCertDate(cert, date);
    }

    public static X509CRL loadCRLObject(String url) throws IOException {
        return CRLService.getX509CRLfromURL(url);
    }

    public static X509CRL loadCRLObject(InputStream instream) throws IOException {
        return CRLService.getX509CRLfromInputStream(instream);
    }

    public static boolean checkCRL(String certSno, X509CRL x509crl) {
        boolean crl_flag = true;
        String crlSno = null;
        Set setEntries = x509crl.getRevokedCertificates();
        Iterator it = setEntries.iterator();
        while (it.hasNext()) {
            X509CRLEntry cre = (X509CRLEntry) it.next();
            crlSno = cre.getSerialNumber().toString();
            if (crlSno.equals(certSno)) {
                crl_flag = false;
                break;
            }
        }
        return crl_flag;
    }

    public static Map filterCertMapByCRL(Map certMap) throws NoSuchAlgorithmException, CertificateEncodingException, UnsupportedEncodingException {
        Map crlMap = getCRLMapFromCertMap(certMap);
        Map newMap = new HashMap();
        String certAlias = null;
        X509Certificate cert = null;
        Iterator it = certMap.keySet().iterator();
        CAParamBean caParam = null;
        Map paramMap = null;
        X509CRL crlObj = null;
        String aki = null;
        String srNo = null;
        String crl = null;
        outer:
        while (it.hasNext()) {
            certAlias = (String) it.next();
            cert = (X509Certificate) certMap.get(certAlias);
            paramMap = CertVerificationService.getCertParams(cert);
            aki = (String) paramMap.get(CERT_PARAM_AKI);
            srNo = (String) paramMap.get(CERT_PARAM_SRNO);
            crlObj = (X509CRL) crlMap.get(aki);
            if (crlObj == null || checkCRL(srNo, crlObj)) {
                newMap.put(certAlias, cert);
            }
        }
        return newMap;
    }

    public static Map getCRLMapFromCertMap(Map certMap) throws NoSuchAlgorithmException, CertificateEncodingException, UnsupportedEncodingException {
        Map crlMap = new HashMap();
        String certAlias = null;
        X509Certificate cert = null;
        X509CRL crlObj = null;
        Iterator it = certMap.keySet().iterator();
        CAParamBean caParam = null;
        Map paramMap = null;
        String aki = null;
        String crl = null;
        outer:
        while (it.hasNext()) {
            certAlias = (String) it.next();
            cert = (X509Certificate) certMap.get(certAlias);
            paramMap = CertVerificationService.getCertParams(cert);
            aki = (String) paramMap.get(CERT_PARAM_AKI);
            crl = (String) paramMap.get(CERT_PARAM_CRL);
            if (crlMap.containsKey(aki)) {
                continue outer;
            }
            try {
                crlObj = loadCRLObject(crl);
                crlMap.put(aki, crlObj);
            } catch (Exception ioe) {
                
                System.out.println("Unable to connect to CRL location.");
                logger.info("ERROR", ioe);
            }

        }
        return crlMap;
    }

    public static void main(String[] args) {
        X509Certificate cert = null;
        byte[] b = null;
        Map certParam = null;
        try {

            FileInputStream fin = new FileInputStream("c:\\SandeepShah.cer");
            b = new byte[fin.available()];
            //fin.read(b);
            CertificateFactory cf = CertificateFactory.getInstance("X509");

            cert = (X509Certificate) cf.generateCertificate(fin);

            String certEncoded = Base64Service.encode(cert.getEncoded());
            System.out.println("Cert String " + certEncoded);
            certParam = CertVerificationService.getCertParams(cert);

        } catch (Exception ex) {
            logger.info("ERROR", ex);
        }

    }

    public static boolean certRootVerification(KeyStore ks, String alias) throws KeyStoreException, CertificateEncodingException, NoSuchAlgorithmException, UnsupportedEncodingException {
        String rootSKI = parser.getRootSki();
        Certificate[] certs = ks.getCertificateChain(alias);
        X509Certificate cert = (X509Certificate) certs[certs.length - 1];
        Map params = getCertParams(cert);
        String ski = (String) params.get(CERT_PARAM_SKI);
        if (ski == null || ski.trim().length() == 0) {
            return false;
        }
        return (rootSKI.indexOf(ski) >= 0);
    }

    public static boolean certChainVerification(KeyStore ks, String alias) throws KeyStoreException, CertificateEncodingException, NoSuchAlgorithmException, UnsupportedEncodingException {

        Certificate[] certs = ks.getCertificateChain(alias);
        try {
            if (certs == null || certs.length == 0) {
                return false;
            }
            CertChainServices.validateChainSignature(certs);
            return true;
        } catch (UnableToProcessChainVerification ex) {
            return false;
        } catch (ChainVerificationException ex) {
            logger.info("ERROR", ex);
            return false;
        }

    }

    public static boolean certDateVerification(X509Certificate xcert, Date dt) {
        return validateCertDate(xcert, dt);
    }

    public static boolean certCRLVerification(X509Certificate xcert) {

        try {
            CRLValidator validator = CRLValidator.getInstance(xcert);
            return !validator.isReavocked();
        } catch (Exception ex) {
            System.out.println("CRL verification Error :- " + ex);
            logger.info("ERROR", ex);
            return true;
        }

    }

    public static boolean certCRLVerificationCheckException(X509Certificate xcert) throws NoSuchAlgorithmException, CertificateEncodingException, UnsupportedEncodingException, IOException, NullPointerException, CertStoreException {

        CRLValidator validator = CRLValidator.getInstance(xcert);
        return !validator.isReavocked();

    }

    public static boolean certCRLVerification(X509Certificate xcert, InputStream ins) throws NoSuchAlgorithmException, CertificateEncodingException, UnsupportedEncodingException, IOException {
        Map params = getCertParams(xcert);
        String crlStr = (String) params.get(CERT_PARAM_CRL);
        X509CRL crl = CRLService.getX509CRLfromInputStream(ins);
        return (!crl.isRevoked(xcert));
    }

    public static boolean certIssuerVerification(X509Certificate xcert) throws CertificateEncodingException, NoSuchAlgorithmException, UnsupportedEncodingException {
        Map caMap = parser.getCAMap();
        Map params = getCertParams(xcert);
        String aki = (String) params.get(CERT_PARAM_AKI);
        CAParamBean bean = (CAParamBean) caMap.get(aki);
        return (bean != null);
    }

    public static boolean certCAVerification(KeyStore ks, String alias) throws KeyStoreException, NoSuchAlgorithmException, CertificateEncodingException, UnsupportedEncodingException {
        Map caMap = parser.getCAMap();
        String certSki = null;
        X509Certificate cert = null;
        Map certParams = null;
        Certificate[] certs = ks.getCertificateChain(alias);
        if (certs.length == 1) {
            return true;
        }
        if (certs.length >= 2) {
            cert = (X509Certificate) certs[certs.length - 2];
            certParams = getCertParams(cert);
            certSki = (String) certParams.get(CERT_PARAM_SKI);
            CAParamBean cbean = (CAParamBean) caMap.get(certSki);
            return (cbean != null);
        }
        return false;
    }

    public static boolean certClassVerification(X509Certificate xcert) throws CertificateEncodingException, NoSuchAlgorithmException, UnsupportedEncodingException {
        String classes[] = null;
        Map caMap = parser.getCAMap();
        Map params = getCertParams(xcert);
        String certClass = null;
        String aki = (String) params.get(CERT_PARAM_AKI);
        CAParamBean bean = (CAParamBean) caMap.get(aki);
        if (bean == null) {
            classes = new String[]{"1", "1a", "1b"};
        } else {
            classes = bean.getClassBlocked();
        }
        if (classes == null) {
            classes = new String[0];
        }

        certClass = (String) params.get(CERT_PARAM_CERTCLASS);
        for (int i = 0; i < classes.length; i++) {
            if (classes[i].equalsIgnoreCase(certClass)) {
                return false;
            }
        }
        return true;
    }

    public static boolean isSigningAllowed(X509Certificate cert) {
        boolean keyUsage[] = cert.getKeyUsage();
        if (keyUsage == null || keyUsage.length == 0) {
            return false;
        }
        return keyUsage[0];
    }

    public static boolean isEncryptionAllowed(X509Certificate cert) {
        boolean keyUsage[] = cert.getKeyUsage();
        if (keyUsage == null || keyUsage.length < 4) {
            return false;
        }
        return (keyUsage[2] || keyUsage[3]);
    }

    public static boolean isNONRepAllowed(X509Certificate cert) {
        boolean keyUsage[] = cert.getKeyUsage();
        if (keyUsage == null || keyUsage.length < 2) {
            return false;
        }
        return keyUsage[1];
    }

    public static boolean isDOCSigAllowed(X509Certificate cert) {
        boolean result = false;
        if (cert.getVersion() >= 3) {
            try {
                List<String> exOid = cert.getExtendedKeyUsage();
                if (exOid != null) {
                    result = exOid.contains("1.3.6.1.4.1.311.10.3.12");  //1.3.6.1.4.1.311.3.10.3.12 doc signing
                }
            } catch (Exception ex1) {
                logger.info("ERROR", ex1);
            }
        }
        return result;
    }
}
