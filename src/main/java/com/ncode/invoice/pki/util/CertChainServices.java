//$Id: CertChainServices.java,v 1.2 2018/10/29 08:18:07 usshah Exp $
//$Source: /cvsrepo/PKI3/BillApps/ServerSidePDFSignerWeb_HSM/src/java/com/ncode/pki/util/CertChainServices.java,v $
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ncode.invoice.pki.util;

import com.ncode.invoice.chain.exceptions.ChainElemenValidityException;
import com.ncode.invoice.chain.exceptions.ChainElementRevokedException;
import com.ncode.invoice.chain.exceptions.ChainVerificationException;
import com.ncode.invoice.chain.exceptions.InvalidRootException;
import com.ncode.invoice.chain.exceptions.NoFullChainException;
import com.ncode.invoice.chain.exceptions.UnableToProcessChainVerification;
import com.ncode.invoice.pki.crl.CRLValidator;
import com.ncode.invoice.pkiconf.parser.PKIConfParser;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SignatureException;
import java.security.cert.CertStoreException;
import java.security.cert.Certificate;
import java.security.cert.CertificateEncodingException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateExpiredException;
import java.security.cert.CertificateNotYetValidException;
import java.security.cert.X509Certificate;
import java.util.Date;
import java.util.Enumeration;
import java.util.Map;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *

 */
public class CertChainServices {
    
    private static final Logger logger = LogManager.getLogger(CertChainServices.class);

    /**
     * this variable holds value of saperator with which all String converted
     * certificates saperated.
     */
    private static String CERT_STR_SAPERATOR = ",";

    /**
     * this function will convert supplied certificate chain to delimeter
     * saperated staing.
     *
     * @param chain Certificate chain (Array of Certificate)
     * @return String convertd value of Certificate chain.
     * @throws UnableToProcessChainVerification If unable to encode chain to
     * base64 string.
     */
    public static String convertChainToString(Certificate[] chain) throws UnableToProcessChainVerification {
        String strChain = "";
        if (chain == null || chain.length == 0) {
            return null;
        }
        try {
            for (int i = 0; i < chain.length; i++) {
                if (i > 0) {
                    strChain += CERT_STR_SAPERATOR;
                }
                strChain = strChain + Base64Service.encode(chain[i].getEncoded());
            }
        } catch (CertificateEncodingException ce) {
            throw new UnableToProcessChainVerification("Unable to conver certificate chain to string");
        }
        return strChain;
    }

    /**
     * It converts base 64 converted String to Certificate chain.
     *
     * @param strChain base64 converted String of Certificate chain.
     * @return Array of Certificate as a chain.
     * @throws UnableToProcessChainVerification
     */
    public static Certificate[] convertStringToCertChain(String strChain) throws UnableToProcessChainVerification {
        Certificate[] chain = null;
        String strCerts[] = null;
        if (strChain == null || strChain.trim().length() == 0) {
            return null;
        }
        strCerts = strChain.split(CERT_STR_SAPERATOR);
        chain = new Certificate[strCerts.length];
        try {
            for (int i = 0; i < strCerts.length; i++) {
                chain[i] = CertificateService.loadCertificateFromBase64(strCerts[i]);
            }
        } catch (CertificateException ce) {
            throw new UnableToProcessChainVerification("Unable to convert string to certificate chain");
        } catch (IOException ioe) {
            throw new UnableToProcessChainVerification("Unable to convert string to certificate chain");
        }
        return chain;
    }

    /**
     * It will return user certificate from certificate chain
     *
     * @param chain Certificate chain of user
     * @return User's Certificate.
     */
    public static X509Certificate getUserCertificate(Certificate[] chain) {
        if (chain != null && chain.length > 0) {
            return (X509Certificate) chain[0];
        } else {
            return null;
        }
    }

    /**
     * It will return CCA India's Certificate, from certificate chain.
     *
     * @param chain Certificate chain of user
     * @return CCA India's (ROOT CA) Certificate.
     * @throws NoFullChainException thrown when full chain does not exists.
     * @throws UnableToProcessChainVerification thrown when unable to process
     * chain validation.
     * @throws InvalidRootException if chain is not under CCA india.
     */
    public static X509Certificate getCCAIndiaCertificate(Certificate[] chain) throws NoFullChainException, UnableToProcessChainVerification, InvalidRootException {
        isFullChainExists(chain);
        validateChainUnderCCAINDIA(chain);
        return (X509Certificate) chain[chain.length - 1];
    }

    /**
     *
     * @param chain Certificate chain of user
     * @return CA's Certificate
     * @throws NoFullChainException thrown when full chain does not exists.
     * @throws UnableToProcessChainVerification thrown when unable to process
     * chain validation.
     * @throws InvalidRootException if chain is not under CCA india.
     */
    public static X509Certificate getCACertificate(Certificate[] chain) throws NoFullChainException, UnableToProcessChainVerification, InvalidRootException {
        isFullChainExists(chain);
        validateChainUnderCCAINDIA(chain);
        return (X509Certificate) chain[chain.length - 2];
    }

    /**
     * it checks if the whole certificate chain is exists or not. It Should have
     * at lease 3 certificates on chain, Individual, CA, CCA. and the top level
     * certificate must be the CCA certificate
     *
     * @param chain Certificate chian
     * @throws NoFullChainException if Full chain does not exists.
     * @throws InvalidRootException if Chain doesn't fall under CCA India.
     * @throws UnableToProcessChainVerification
     */
    public static void isFullChainExists(Certificate[] chain) throws NoFullChainException, InvalidRootException, UnableToProcessChainVerification {
        if (chain != null && chain.length >= 3) {
            validateChainUnderCCAINDIA(chain);
        } else {
            throw new NoFullChainException();
        }
    }

    /**
     * It Checks if Chain falls under CCA INDIA.
     *
     * @param chain Certificate chain
     * @throws UnableToProcessChainVerification if unable to process or any
     * unexpected error ocured.
     * @throws InvalidRootException if chain doesn't fall under CCA India.
     */
    public static void validateChainUnderCCAINDIA(Certificate[] chain) throws InvalidRootException, UnableToProcessChainVerification {
        X509Certificate cert = null;
        String thumbPrint = null;
        Map certParams = null;
        cert = (X509Certificate) chain[chain.length - 1];
        try {
            certParams = CertVerificationService.getCertParams(cert);
            thumbPrint = (String) certParams.get(CertVerificationService.CERT_PARAM_THUMBPRINT);
            if (!(thumbPrint != null && PKIConfParser.rootThumbprint.indexOf(thumbPrint) >= 0)) {
                throw new InvalidRootException();
            }
        } catch (CertificateEncodingException ce) {
            throw new UnableToProcessChainVerification("Unable to check existsnce of ROOT CA in certificate chain.");
        } catch (NoSuchAlgorithmException ne) {
            throw new UnableToProcessChainVerification("Unable to check existsnce of ROOT CA in certificate chain.");
        } catch (UnsupportedEncodingException ex) {
            throw new UnableToProcessChainVerification("Unable to check existsnce of ROOT CA in certificate chain.");
        }
    }

    /**
     *
     * @param chain
     * @param currentdate
     * @throws ChainElemenValidityException
     */
    public static void validateExpieryDate(Certificate[] chain, Date currentdate) throws ChainElemenValidityException {
        X509Certificate cert = null;
        for (int i = 0; i < chain.length; i++) {
            cert = (X509Certificate) chain[i];
            try {
                cert.checkValidity(currentdate);
            } catch (CertificateExpiredException de) {
                throw new ChainElemenValidityException("Atlease on of the certificate expired in chain.");
            } catch (CertificateNotYetValidException de) {
                throw new ChainElemenValidityException("Atlease on of the certificate validity not yet started in chain.");
            }
        }
    }

    /**
     *
     * @param chain
     * @throws ChainElementRevokedException
     * @throws UnableToProcessChainVerification
     */
    public static void validateCertRevokation(Certificate[] chain) throws ChainElementRevokedException, UnableToProcessChainVerification {
        X509Certificate cert = null;
        for (int i = 0; i < chain.length; i++) {
            cert = (X509Certificate) chain[i];
            try {
                CRLValidator val = CRLValidator.getInstance(cert);
                if (val.isReavocked()) {
                    throw new ChainElementRevokedException();
                }
            } catch (CertStoreException de) {
                throw new UnableToProcessChainVerification("Unable to parse CRL File");
            } catch (IOException de) {
                throw new UnableToProcessChainVerification("Unable to connect CRL location");
            }
        }
    }

    /**
     *
     * @param chain
     * @throws ChainVerificationException
     */
    public static void validateChainSignature(Certificate[] chain) throws UnableToProcessChainVerification, ChainVerificationException {
        X509Certificate cert = null;
        X509Certificate parentCert = null;
        for (int i = 0; i < chain.length - 1; i++) {
            cert = (X509Certificate) chain[i];
            parentCert = (X509Certificate) chain[i + 1];

            try {
                cert.verify(parentCert.getPublicKey());
            } catch (CertificateException de) {
                throw new UnableToProcessChainVerification("Unable to parse CRL File");
            } catch (InvalidKeyException de) {
                throw new UnableToProcessChainVerification("Unable to connect CRL location");
            } catch (NoSuchAlgorithmException de) {
                throw new UnableToProcessChainVerification("Unable to connect CRL location");
            } catch (NoSuchProviderException de) {
                throw new UnableToProcessChainVerification("Unable to connect CRL location");
            } catch (SignatureException de) {
                throw new ChainVerificationException("Signature verification on chain is failed.");
            }
        }
    }

    /**
     *
     * @param chain
     * @throws ChainVerificationException
     */
    public static void validateChainAKISKI(Certificate[] chain) throws ChainVerificationException {
        X509Certificate cert = null;
        X509Certificate parentCert = null;
        for (int i = 0; i < chain.length - 1; i++) {
            cert = (X509Certificate) chain[i];
            parentCert = (X509Certificate) chain[i + 1];

            try {
                cert.verify(parentCert.getPublicKey());
            } catch (CertificateException de) {
                throw new UnableToProcessChainVerification("Unable to parse CRL File");
            } catch (InvalidKeyException de) {
                throw new UnableToProcessChainVerification("Unable to connect CRL location");
            } catch (NoSuchAlgorithmException de) {
                throw new UnableToProcessChainVerification("Unable to connect CRL location");
            } catch (NoSuchProviderException de) {
                throw new UnableToProcessChainVerification("Unable to connect CRL location");
            } catch (SignatureException de) {
                throw new ChainVerificationException("Signature verification on chain is failed.");
            }
        }
    }

    /**
     *
     * @param chain
     * @param currentDate
     * @throws UnableToProcessChainVerification
     * @throws InvalidRootException
     * @throws ChainElemenValidityException
     * @throws ChainElementRevokedException
     * @throws ChainVerificationException
     */
    public static void validateCertChain(Certificate[] chain, Date currentDate) throws UnableToProcessChainVerification, InvalidRootException, ChainElemenValidityException, ChainVerificationException {
        validateChainUnderCCAINDIA(chain);
        validateExpieryDate(chain, currentDate);
        //validateCertRevokation(chain);
        validateChainSignature(chain);
    }

    public static void main(String[] s) throws KeyStoreException {
        try {
            KeyStore ks = KeyStore.getInstance("JKS");
            ks.load(new FileInputStream("c:\\testks.jks"), "password".toCharArray());
            Enumeration en = ks.aliases();
            while (en.hasMoreElements()) {
                System.out.println((String) en.nextElement());
            }
        } catch (Exception ex) {
            logger.info("Error", ex);
        }
    }

}
