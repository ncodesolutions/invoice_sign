package com.ncode.invoice.pki.util;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 
 */
public class MimeTypes {

    public final static String EXT_JPG = "jpg";
    public final static String EXT_JPEG = "jpeg";
    public final static String EXT_JPE = "jpe";
    public final static String MIME_JPG = "image/jpeg";
    public final static String EXT_GIF = "gif";
    public final static String MIME_GIF = "image/gif";
    public final static String EXT_BMP = "bmp";
    public final static String MIME_BMP = "image/bmp";
    public final static String EXT_PNG = "png";
    public final static String MIME_PNG = "image/png";

    public final static String EXT_TXT = "txt";
    public final static String MIME_TXT = "text/plain";
    public final static String EXT_DOC = "doc";
    public final static String EXT_DOCX = "docx";
    public final static String MIME_DOC = "application/msword";
    public final static String EXT_XLS = "xls";
    public final static String EXT_XLSX = "xlsx";
    public final static String MIME_XLS = "application/vnd.ms-excel";
    public final static String EXT_PDF = "pdf";
    public final static String MIME_PDF = "application/pdf";

    public final static String EXT_ZIP = "zip";
    public final static String MIME_ZIP = "application/zip";

    public final static String MIME_OCTET = "application/octet-stream";

    public static String getMimeTypeFromFile(String fileName) {
        String fileExt = fileName != null && fileName.trim().length() > 0
                ? fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length()) : null;
        String mimeType = getMimeTypeFromExt(fileExt);
        return mimeType;
    }

    public static String getMimeTypeFromExt(String fileExt) {
        if (fileExt.equalsIgnoreCase(EXT_JPG) || fileExt.equalsIgnoreCase(EXT_JPE) || fileExt.equalsIgnoreCase(EXT_JPEG)) {
            return MIME_JPG;
        } else if (fileExt.equalsIgnoreCase(EXT_GIF)) {
            return MIME_GIF;
        } else if (fileExt.equalsIgnoreCase(EXT_BMP)) {
            return MIME_BMP;
        } else if (fileExt.equalsIgnoreCase(EXT_PNG)) {
            return MIME_PNG;
        } else if (fileExt.equalsIgnoreCase(EXT_TXT)) {
            return MIME_TXT;
        } else if (fileExt.equalsIgnoreCase(EXT_DOC) || fileExt.equalsIgnoreCase(EXT_DOCX)) {
            return MIME_DOC;
        } else if (fileExt.equalsIgnoreCase(EXT_XLS) || fileExt.equalsIgnoreCase(EXT_XLSX)) {
            return MIME_XLS;
        } else if (fileExt.equalsIgnoreCase(EXT_PDF)) {
            return MIME_PDF;
        } else {
            return MIME_OCTET;
        }
    }

}
