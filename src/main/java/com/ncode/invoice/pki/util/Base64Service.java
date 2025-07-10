/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ncode.invoice.pki.util;

import java.io.IOException;
//import sun.misc.BASE64Decoder;
//import sun.misc.BASE64Encoder;

/**
 * Class is used to convert byte array into Base64 encoding and decode same and
 * convert byte array to hexa decimal to get proper values from certificate.
 *

 */
public class Base64Service {

    //private final static BASE64Encoder encoder= new BASE64Encoder();
    //private final static BASE64Decoder decoder= new BASE64Decoder();
    /**
     * Method is used to encode byte array in Base64.
     *
     * @param dataToEncode String object to encode.
     * @return Base64 converted String object.
     */
    public static String encode(byte[] dataToEncode) {
        //return encoder.encode(dataToEncode);
        //return new String(Base64Coder.encode(dataToEncode));
        return Base64New.encode(dataToEncode);
    }

    /*public static void encode(InputStream inStrToEncode,OutputStream enodedOut) throws IOException
    {
        enc.encode(inStrToEncode,enodedOut);
    }*/
    /**
     * Method to convert byte array from encoded string.
     *
     * @param dataToDecode String object to be decode.
     * @return byte array of decoded string.
     */
    public static byte[] decode(String dataToDecode) throws IOException {
        //return decoder.decodeBuffer(dataToDecode);
        //return Base64Coder.decode(dataToDecode);
        while (dataToDecode.length() < 4) {
            dataToDecode += "=";
        }
        return Base64New.decode(dataToDecode);
    }

    /*public static void decode(InputStream inStrToDecode,OutputStream decodedOut) throws IOException
    {
        dec.decodeBuffer(inStrToDecode, decodedOut);
    }*/
    /**
     * Method is used to convert byte array to Hexa decimal String.
     *
     * @param buf byte array of data.
     * @param start Starting point of array.
     * @return converted String of byte array.
     */
    public static String convertToString(byte[] buf, int start) {
        int len = buf.length;
        StringBuffer convertData = new StringBuffer();
        String returnString = null;
        for (int i = start; i < len; i++) {
            String HEX = "0123456789abcdef";
            convertData.append(" ");
            convertData.append(HEX.charAt(buf[i] >>> 4 & 0x0F));
            convertData.append(HEX.charAt(buf[i] & 0x0F));
        }
        returnString = new String(convertData);
        return returnString;
    }

    public static void main(String[] s) {
        byte[] data = null;
        try {
            data = decode("JSK");
        } catch (Exception ex) {
            ex.printStackTrace(System.out);
        }
    }
}
