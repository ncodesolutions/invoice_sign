/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ncode.invoice.pki.util;

import com.ncode.invoice.pkiconf.parser.CAParamBean;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.KeyStoreSpi;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateEncodingException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Collection;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *

 */
public class CertificateService {

    private static final Logger logger = LogManager.getLogger(CertificateService.class);
    
    /**
     * This function is added to solve the problem with duplicate aliases.
     *
     * @param keyStore targeted key store which has duplicate aliases.
     */
    private static void _fixAliases(java.security.KeyStore keyStore) {
        Field field;
        KeyStoreSpi keyStoreVeritable;

        try {
            field = keyStore.getClass().getDeclaredField("keyStoreSpi");
            field.setAccessible(true);
            keyStoreVeritable = (KeyStoreSpi) field.get(keyStore);

            if ("sun.security.mscapi.KeyStore$MY".equals(keyStoreVeritable.getClass().getName())) {
                Collection entries;
                String alias, hashCode;
                X509Certificate[] certificates;

                field = keyStoreVeritable.getClass().getEnclosingClass().getDeclaredField("entries");
                field.setAccessible(true);
                entries = (Collection) field.get(keyStoreVeritable);

                for (Object entry : entries) {
                    field = entry.getClass().getDeclaredField("certChain");
                    field.setAccessible(true);
                    certificates = (X509Certificate[]) field.get(entry);

                    hashCode = Integer.toString(certificates[0].hashCode());

                    field = entry.getClass().getDeclaredField("alias");
                    field.setAccessible(true);
                    alias = (String) field.get(entry);

                    if (!alias.equals(hashCode)) {
                        field.set(entry, alias.concat(" - ").concat(hashCode));
                    } // if
                } // for
            } // if
        } catch (Exception exception) {
            logger.info("ERROR", exception);
            } // catch
    } // _fixAliases

    /**
     * Method id used to get KeyStore of MicroSoft CertStore.
     *
     * @return default KeyStore.
     * @throws java.security.KeyStoreException
     */
    public static KeyStore getKeyStore() throws KeyStoreException, IOException, NoSuchAlgorithmException, CertificateException {
        KeyStore ks = KeyStore.getInstance("Windows-My");
        ks.load(null, null);
        _fixAliases(ks);
        return ks;
    }

    /**
     * Methos is used to get all the certificate from the KeyStore object.
     *
     * @param keystore KeyStore from which certfiacte are to be retrieved.
     * @return Map<Cert alias,X509Certificate Obj> of all the certificates.
     * @throws java.io.IOException.
     * @throws java.security.NoSuchAlgorithmException.
     * @throws java.security.KeyStoreException.
     * @throws java.security.cert.CertificateException.
     */
    public static Map getCertsFromKeystore(KeyStore keystore) throws KeyStoreException {
        String alias = null;
        Map certMap = new HashMap();
        //keystore.load(null,null);
        int keyStore_size = keystore.size();
        Enumeration en = keystore.aliases();
        X509Certificate cert = null;
        if (keyStore_size == 0) {
            return certMap;
        }
        while (en.hasMoreElements()) {
            alias = (String) en.nextElement();
            cert = (X509Certificate) keystore.getCertificate(alias);

            certMap.put(alias, cert);
        }
        return certMap;
    }

    /**
     * Method is used to retrieve certificates from keyStore and validating
     * using Configuration file, it will not check for date validation.
     *
     * @param ks KeyStore object to retrieve certificates.
     * @param confCaMap Certificate authority Map retrieve from Configuration
     * file.
     * @return Map<Cert alias,X509Certificate Obj> of all valid certificates.
     * @throws java.security.KeyStoreException
     * @throws java.security.NoSuchAlgorithmException
     * @throws java.security.cert.CertificateEncodingException
     * @throws java.io.UnsupportedEncodingException
     */
    public static Map getFilterCertMap(KeyStore ks, Map confCaMap) throws KeyStoreException, UnsupportedEncodingException, CertificateEncodingException, NoSuchAlgorithmException, UnrecoverableKeyException {
        return getFilterCertMap(ks, confCaMap, null);
    }

    /**
     * Method is used to retrieve certificates from keyStore and validating
     * using Configration file and also validate certficate by date.
     *
     * @param ks KeyStore object to retrieve certificates.
     * @param confCaMap Certificate authority Map retrieve from Configuration
     * file.
     * @param curDate Server Date as Date Object.
     * @return Map<Cert alias,X509Certificate Obj> of all valid certificates.
     * @throws java.security.KeyStoreException
     * @throws java.security.NoSuchAlgorithmException
     * @throws java.security.cert.CertificateEncodingException
     * @throws java.io.UnsupportedEncodingException
     */
    public static Map getFilterCertMap(KeyStore ks, Map confCaMap, Date curDate) throws KeyStoreException, NoSuchAlgorithmException, CertificateEncodingException, UnsupportedEncodingException {
        Map certMap = new HashMap();
        CAParamBean caParam = null;
        String certAlias = null;
        X509Certificate cert = null;
        Map paramMap = null;
        String classes[] = null;
        String cls = null;
        Enumeration it = ks.aliases();
        outer:
        while (it.hasMoreElements()) {
            certAlias = (String) it.nextElement();
            cert = (X509Certificate) ks.getCertificate(certAlias);
            paramMap = CertVerificationService.getCertParams(cert);
            caParam = (CAParamBean) confCaMap.get(paramMap.get(CertVerificationService.CERT_PARAM_AKI));
            if (caParam == null) {
                continue;
            }
            classes = caParam.getClassBlocked();
            cls = (String) paramMap.get(CertVerificationService.CERT_PARAM_CERTCLASS);
            if (classes != null && classes.length > 0) {
                inner:
                for (int i = 0; i < classes.length; i++) {
                    if (classes[i].equalsIgnoreCase(cls)) {
                        continue outer;
                    }
                }
            }
            if (curDate != null && !CertVerificationService.validateCertDate(cert, curDate)) {
                continue outer;
            }

            certMap.put(certAlias, cert);
        }
        return certMap;
    }

    /**
     * Method is used to validate certificates according to Curdate.
     *
     * @param certMap Map of all certificate.
     * @param curDate Server date as Date object.
     * @return Map<Cert alias,X509Certificate Obj> of all valid certificates.
     */
    public static Map filterCertMapByDate(Map certMap, Date curDate) {
        Map newMap = new HashMap();
        String certAlias = null;
        X509Certificate cert = null;
        Iterator it = certMap.keySet().iterator();
        outer:
        while (it.hasNext()) {
            certAlias = (String) it.next();
            cert = (X509Certificate) certMap.get(certAlias);
            if (!CertVerificationService.validateCertDate(cert, curDate)) {
                continue outer;
            }
            newMap.put(certAlias, cert);
        }
        return newMap;
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

    /*   public static PublicKey loadRSAPublicKeyFromBase64(String pubKey) throws InvalidKeySpecException, NoSuchAlgorithmException, IOException
    {
        PublicKey serverPubKey=null;
        PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(Base64Service.decode(pubKey));
        KeyFactory keyFact = KeyFactory.getInstance("RSA");
        serverPubKey = keyFact.generatePublic(spec);
        return serverPubKey;
    }
     */
    public static PrivateKey loadRSAPrivateKeyFromBase64(String prvKeyStr) throws IOException, NoSuchAlgorithmException, InvalidKeySpecException {
        PrivateKey prvKey = null;
        PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(Base64Service.decode(prvKeyStr));
        KeyFactory keyFact = KeyFactory.getInstance("RSA");
        prvKey = keyFact.generatePrivate(spec);
        return prvKey;
    }

    public static KeyPair generateKeyPair(String algorithm, int keySize) throws NoSuchAlgorithmException {
        KeyPair kp = null;
        KeyPairGenerator kg = KeyPairGenerator.getInstance(algorithm);
        kg.initialize(keySize);
        kp = kg.generateKeyPair();
        return kp;
    }

    public static KeyPair generateKeyPair() throws NoSuchAlgorithmException {
        return generateKeyPair("RSA", 1024);
    }

}
