/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ncode.invoice.pkiconf.parser;

import java.util.HashMap;
import java.util.Map;

/**
 * Class is use to get all the details from pkiConf.xml file to show certificate
 * according to requirment. This class parse xml file and return all details
 * store in xml file.
 *

 */
public class PKIConfParser {

    /**
     * As new ROOT CA and CA's introduces following line is changed and New ROOT
     * SKI is added by chintan 16/03/2011
     */
    //public final static String rootSki = "4f 1e c0 58 27 d8 b8 e4,4e d4 62 6c 0b 09 61 96"; // Test
    //public final static String rootSki = "4f 1e c0 58 27 d8 b8 e4"; //Original
    public final static String rootSki = "4f 1e c0 58 27 d8 b8 e4," //original - 2007
            + "4d 07 a8 63 f2 db 1c df," //Original - 2011
            + "4e f8 62 d3 d4 4e 62 bc," //Test CCA - 2012
            + "42 b8 c5 cf 6d b3 57 e1"; // CCA 2014 valid till 2024 // ADDED by Umang
    public final static String rootThumbprint = "97 22 6a ae 4a 7a 64 a5 9b d1 67 87 f2 7f 84 1c 0a 00 1f d0,"
            + "43 6a 58 18 e0 81 f3 53 a4 82 a9 ac d0 a5 a7 b7 03 0c 18 aa,"
            + "be d5 25 d1 ac 63 a7 fc 6a 66 0b a7 a8 95 81 8d 5e 8d d5 64,"
            + "94 59 48 3f a8 63 0f 9e 7a 0c cf 59 5f 72 63 51 fa 67 49 72,"
            + "‎a2 b8 6b 5a 68 d9 28 19 d9 ce 5d d6 d7 96 9a 49 68 e1 19 91"; //CCA 2014 valid till 2024 // ADDED by Umang
    private final static HashMap caMap = new HashMap();
    private final static String[] blockedClasses = new String[]{"1", "1a"};

    static {
        //CAParamBean ca = null;
        //CA Certs under RCAI 2007- start
        CAParamBean ca1 = new CAParamBean("4c c2 82 c2 5e 14 a8 36");
        ca1.setName("(n)Code new - EXP_DATE: 4th JUL 2015");
        ca1.setCrl("https://www.ncodesolutions.com/repository/ncodecrlc1.crl");
        ca1.setClassBlocked(blockedClasses);
        caMap.put(ca1.getId(), ca1);

        CAParamBean ca2 = new CAParamBean("41 88 93 a6 34 68 4a de");
        ca2.setName("e-Mudhra CA - EXP_DATE: 4th JUL 2015");
        ca2.setCrl("");
        ca2.setClassBlocked(blockedClasses);
        caMap.put(ca2.getId(), ca2);

        CAParamBean ca3 = new CAParamBean("46 0c 24 a0 b0 55 4c 76");
        ca3.setName("IDRBT CERTIFYING AUTHORITY - EXP_DATE: 4th JUL 2015");
        ca3.setCrl("");
        ca3.setClassBlocked(blockedClasses);
        caMap.put(ca3.getId(), ca3);

        CAParamBean ca4 = new CAParamBean("40 2c 8f 3a ae 1c aa 1e");
        ca4.setName("mtnlTrustLine Public Primary Certification Authority G1 - EXP_DATE: 4th JUL 2015");
        ca4.setCrl("");
        ca4.setClassBlocked(blockedClasses);
        caMap.put(ca4.getId(), ca4);

        CAParamBean ca5 = new CAParamBean("4c 27 7b 6e 0d b2 ff 54");
        ca5.setName("NIC Certifying Authority - EXP_DATE: 4th JUL 2015");
        ca5.setCrl("");
        ca5.setClassBlocked(blockedClasses);
        caMap.put(ca5.getId(), ca5);

        CAParamBean ca6 = new CAParamBean("42 90 3c 5a 0f b6 8e c4");
        ca6.setName("Safescrypt India-RCAI Class 2 CA-G2  - EXP_DATE: 4th JUL 2015");
        ca6.setCrl("");
        ca6.setClassBlocked(blockedClasses);
        caMap.put(ca6.getId(), ca6);

        CAParamBean ca7 = new CAParamBean("41 ee 78 34 37 b3 37 8d");
        ca7.setName("Safescript CA under RCAI 2007 issued on 6th April 2011 - EXP_DATE: 4th JUL 2015");
        ca7.setCrl("");
        ca7.setClassBlocked(blockedClasses);
        caMap.put(ca7.getId(), ca7);

        CAParamBean ca8 = new CAParamBean("4b 4c fc 63 e8 ac d2 b9");
        ca8.setName("Safescrypt India-RCAI Class 1 CA-G2 - EXP_DATE: 4th JUL 2015");
        ca8.setCrl("");
        ca8.setClassBlocked(blockedClasses);
        caMap.put(ca8.getId(), ca8);

        CAParamBean ca9 = new CAParamBean("43 40 9b 1d ff 99 85 ca");
        ca9.setName("Safescrypt India-RCAI Class 3 CA-G2 - EXP_DATE: 4th JUL 2015");
        ca9.setCrl("");
        ca9.setClassBlocked(blockedClasses);
        caMap.put(ca9.getId(), ca9);

        CAParamBean ca10 = new CAParamBean("49 b9 4a 34 59 2c 24 a1");
        ca10.setName("Tata Consultancy Services Certifying Authority  - EXP_DATE: 4th JUL 2015");
        ca10.setCrl("");
        ca10.setClassBlocked(blockedClasses);
        caMap.put(ca10.getId(), ca10);
        //CA Certs under RCAI 2007- END

        CAParamBean ca11 = new CAParamBean("47 90 2f 97 3d ee 04 12");
        ca11.setName("(n)Code Solutions CA 2011-1 - EXP_DATE : 11th MAR 2016");
        ca11.setCrl("");
        ca11.setClassBlocked(blockedClasses);
        caMap.put(ca11.getId(), ca11);

        CAParamBean ca12 = new CAParamBean("4e c9 b8 84 fe e4 88 1f");
        ca12.setName("e-Mudhra CA 2011  - EXP_DATE : 11th MAR 2016");
        ca12.setCrl("");
        ca12.setClassBlocked(blockedClasses);
        caMap.put(ca12.getId(), ca12);

        CAParamBean ca13 = new CAParamBean("4b 61 0b 25 1e e0 cd 7f");
        ca13.setName("IDRBT CA 2011  - EXP_DATE : 11th MAR 2016");
        ca13.setCrl("");
        ca13.setClassBlocked(blockedClasses);
        caMap.put(ca13.getId(), ca13);

        CAParamBean ca14 = new CAParamBean("4e 55 4f ae b3 df a1 66");
        ca14.setName("NIC CA 2011  - EXP_DATE : 11th MAR 2016");
        ca14.setCrl("");
        ca14.setClassBlocked(blockedClasses);
        caMap.put(ca14.getId(), ca14);

        CAParamBean ca15 = new CAParamBean("40 cd 66 98 bc ba 79 9a");
        ca15.setName("SafeScrypt CA 2011  - EXP_DATE : 11th MAR 2016");
        ca15.setCrl("");
        ca15.setClassBlocked(blockedClasses);
        caMap.put(ca15.getId(), ca15);

        CAParamBean ca16 = new CAParamBean("46 ae 34 71 30 60 d4 cb");
        ca16.setName("TCS CA 2011  - EXP_DATE : 11th MAR 2016");
        ca16.setCrl("");
        ca16.setClassBlocked(blockedClasses);
        caMap.put(ca16.getId(), ca16);
        //CA UNDER RCAI-2011 : END

        //  Added by Rahul Haldiya Testing of CCA, CA Certificates
        //CA Certs under RCAI 2012 START
        CAParamBean ca17 = new CAParamBean("44 d5 7e 42 4a 92 e7 97"); //SKI of CA
        ca17.setName("nCode CA 2012  - EXP_DATE : 20th Nov 2022");
        ca17.setCrl("");
        ca17.setClassBlocked(blockedClasses);
        caMap.put(ca17.getId(), ca17);

//CA Certs under RCAI 2012 END
//         Added by Umang Shah 11-03-2014 for new CA and CCA certificates START
// (n)code solutions
        CAParamBean ca18 = new CAParamBean("4d 07 be f1 9e 9d fb bd"); //SKI of CA
        ca18.setName("(n)Code Solutions CA 2014  - EXP_DATE : ‎05 ‎March ‎2024 12:00:00 PM");
        ca18.setCrl("");
        ca18.setClassBlocked(blockedClasses);
        caMap.put(ca18.getId(), ca18);

// safescrypts
        CAParamBean ca19 = new CAParamBean("4c 3e 8e 3d 98 02 a5 7e"); //SKI of CA
        ca19.setName("SafeScrypt CA 2014  - EXP_DATE : ‎05 ‎March ‎2024 12:00:00 PM");
        ca19.setCrl("");
        ca19.setClassBlocked(blockedClasses);
        caMap.put(ca19.getId(), ca19);

// safescrypts sub CA
        CAParamBean ca20 = new CAParamBean("43 0e 37 57 e9 27 d9 08"); //SKI of CA
        ca20.setName("SafeScrypt sub-CA for RCAI Class 2 2014 - EXP_DATE : ‎‎05 ‎March ‎2024 10:00:00 AM");
        ca20.setCrl("");
        ca20.setClassBlocked(blockedClasses);
        caMap.put(ca20.getId(), ca20);

        CAParamBean ca21 = new CAParamBean("44 c0 51 a3 0a 21 6d e2"); //SKI of sub-CA
        ca21.setName("SafeScrypt sub-CA for RCAI Class3 2014 - EXP_DATE : ‎‎05 ‎March ‎2024 10:00:00 AM");
        ca21.setCrl("");
        ca21.setClassBlocked(blockedClasses);
        caMap.put(ca21.getId(), ca21);

        CAParamBean ca22 = new CAParamBean("4b 12 f2 4c 63 93 37 47"); //SKI of sub-CA
        ca22.setName("SafeScrypt sub-CA for RCAI Class3 2014 - EXP_DATE : ‎‎05 ‎March ‎2024 10:00:00 AM");
        ca22.setCrl("");
        ca22.setClassBlocked(blockedClasses);
        caMap.put(ca22.getId(), ca22);

// E Mudra
        CAParamBean ca23 = new CAParamBean("49 6c 7a 9d 61 ab f3 77"); //SKI of CA
        ca23.setName("e-Mudhra CA 2014- EXP_DATE : ‎‎05 ‎March ‎2024 10:00:00 AM");
        ca23.setCrl("");
        ca23.setClassBlocked(blockedClasses);
        caMap.put(ca23.getId(), ca23);

// E Mudra sub ca
        CAParamBean ca24 = new CAParamBean("4e 68 a6 7a df 71 56 dc"); //SKI of sub-CA
        ca24.setName("e-Mudhra Sub CA for Class 2 Individual 2014- EXP_DATE : ‎‎05 ‎March ‎2024 10:00:00 AM");
        ca24.setCrl("");
        ca24.setClassBlocked(blockedClasses);
        caMap.put(ca24.getId(), ca24);

        CAParamBean ca25 = new CAParamBean("46 61 0e 14 85 96 08 c0"); //SKI of sub-CA
        ca25.setName("e-Mudhra Sub CA for Class 2 Individual 2014- EXP_DATE : ‎‎05 ‎March ‎2024 10:00:00 AM");
        ca25.setCrl("");
        ca25.setClassBlocked(blockedClasses);
        caMap.put(ca25.getId(), ca25);

        CAParamBean ca26 = new CAParamBean("4b dd bf b7 95 22 8b fe"); //SKI of sub-CA
        ca26.setName(" e-Mudhra Sub CA for Class 3 Device 2014- EXP_DATE : ‎‎05 ‎March ‎2024 10:00:00 AM");
        ca26.setCrl("");
        ca26.setClassBlocked(blockedClasses);
        caMap.put(ca26.getId(), ca26);

        CAParamBean ca27 = new CAParamBean("4b 31 4e e1 00 a4 a9 79"); //SKI of sub-CA
        ca27.setName(" e-Mudhra Sub CA for Class 3 Individual 2014- EXP_DATE : ‎‎05 ‎March ‎2024 10:00:00 AM");
        ca27.setCrl("");
        ca27.setClassBlocked(blockedClasses);
        caMap.put(ca27.getId(), ca27);

        CAParamBean ca28 = new CAParamBean("4c d1 bd 2a 11 48 04 d3"); //SKI of sub-CA
        ca28.setName(" e-Mudhra Sub CA for Class 3 Organisation 2014- EXP_DATE : ‎‎05 ‎March ‎2024 10:00:00 AM");
        ca28.setCrl("");
        ca28.setClassBlocked(blockedClasses);
        caMap.put(ca28.getId(), ca28);

        CAParamBean ca29 = new CAParamBean("45 2b 40 58 a5 ec bf 6a"); //SKI of sub-CA
        ca29.setName(" e-Mudhra Sub CA for DGFT 2014- EXP_DATE : ‎‎05 ‎March ‎2024 10:00:00 AM");
        ca29.setCrl("");
        ca29.setClassBlocked(blockedClasses);
        caMap.put(ca29.getId(), ca29);

// TCS
        CAParamBean ca30 = new CAParamBean("4e f0 8e 33 25 c2 80 4c"); //SKI of CA
        ca30.setName(" TCS CA 2014- EXP_DATE : ‎‎05 ‎March ‎2024 12:00:00 PM");
        ca30.setCrl("");
        ca30.setClassBlocked(blockedClasses);
        caMap.put(ca30.getId(), ca30);

// TCS sub-ca
        CAParamBean ca31 = new CAParamBean("db 6a 0b 9c 34 d3 42 cb df c1 67 88 4e 75 b4 a6 4c 29 04 2b"); //SKI of Sub-CA
        ca31.setName("TCS sub-CA for TCS 2014 - 1 - EXP_DATE : ‎‎‎05 ‎March ‎2024 09:54:27 AM");
        ca31.setCrl("");
        ca31.setClassBlocked(blockedClasses);
        caMap.put(ca31.getId(), ca31);

        CAParamBean ca32 = new CAParamBean("84 e7 76 3c df e3 c9 1b c0 ef a4 89 54 27 d4 fc 4b 93 05 ff"); //SKI of Sub-CA
        ca32.setName("TCS sub-CA for TCS 2014 - 1 - EXP_DATE : ‎‎‎05 ‎March ‎2024 09:54:27 AM");
        ca32.setCrl("");
        ca32.setClassBlocked(blockedClasses);
        caMap.put(ca32.getId(), ca32);

// IDRBT
        CAParamBean ca33 = new CAParamBean("80 75 02 34 07 d4 5e 0e ce 05 6a b5 ac f0 10 6b 42 1b 07 c7"); //SKI of CA
        ca33.setName("IDRBT CA 2014- EXP_DATE : ‎‎‎05 ‎March ‎2024 12:00:00 PM");
        ca33.setCrl("");
        ca33.setClassBlocked(blockedClasses);
        caMap.put(ca33.getId(), ca33);

// NIC
        CAParamBean ca34 = new CAParamBean("46 70 ca 2f 25 4e c3 47"); //SKI of CA
        ca34.setName("NIC CA 2014- EXP_DATE : ‎‎‎05 ‎March ‎2024 12:00:00 PM");
        ca34.setCrl("");
        ca34.setClassBlocked(blockedClasses);
        caMap.put(ca34.getId(), ca34);

//         Added by Umang Shah 11-03-2014 for new CA and CCA certificates END
    }

    public PKIConfParser() {
    }

    public String getRootSki() {
        return rootSki;
    }

    /**
     * Method used to get Map of all certificate provider specify in xml
     * document.
     *
     * @return Map contains all CA.
     */
    public Map getCAMap() {
        return caMap;
    }
}
