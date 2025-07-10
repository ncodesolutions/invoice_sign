/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ncode.invoice.pki.util;

//import java.net.URL;
import java.util.Date;

/**
 *

 */
public class SystemCheck {

    /**
     * This parameter is indicating if system to check expiryDate or not
     */
    private static final boolean checkExpiryDate = true;
    /**
     * This parameter is holding value of expiryDate
     */
    //private static final String expiryDate="01/04/2012";
    private static final String expiryDate = "01/05/2099"; // date format is mm/dd/yyyy

    //    private static final String[] urls = new String[] {"http://localhost:8084",
//                                                        "http://cpthaker:8084",
//                                                        "http://localhost:8080",
//                                                        "http://cpthaker:8080",
//                                                        "http://192.168.10.180:8080",
//                                                        "http://192.168.10.180:8084"
//                                                       };
    /**
     * This Method is for getting the java version currently installed on the
     * machine running the application.
     *
     * @return A string indicating the java version available.
     */
    public static final String getJavaVersion() {
        return System.getProperties().getProperty("java.version");
    }

    /**
     * This Method is for getting the jre version currently installed on the
     * machine running the application.
     *
     * @return A string indicating the jre version available.
     */
    public static final String getJavaRuntimeVersion() {
        return System.getProperties().getProperty("java.runtime.version");
    }

    /**
     * This Method is for checking if Windows OS is installed on the machine
     * running the application.
     *
     * @return A boolean indicating whether Windows OS is installed on the
     * machine.
     */
    public static final boolean isOSWindows() {
        String osName = System.getProperties().getProperty("os.name");
        return (osName.toLowerCase().indexOf("windows") >= 0);
    }

    /*This function will check Expiry Date with Date supplied as parameter and
     * return boolean value 
     * It will return true is component is expired or false otherwise
     * 
     */
    public static final boolean isComponentExpired(String servDate) {
        long curTime = 0;
        long expireTime = 0;
        boolean isExpired = true;
        if (!checkExpiryDate) {
            return false;
        }
        try {
            Date serv_Date = DateUtility.getDateFromString(servDate, DateUtility.DDMMYYPATTERN);
            Date expiry_Date = DateUtility.getDateFromString(expiryDate, DateUtility.DDMMYYPATTERN);
            //System.out.println("Date Parsed OK.");
            curTime = serv_Date.getTime();
            expireTime = expiry_Date.getTime();
            //System.out.println("Expire Time :"+expireTime);
            //System.out.println("Curr Time :"+curTime);
            //System.out.println("Check if expire :"+(expireTime>curTime));
            if (expireTime > curTime) {
                isExpired = false;
            } else {
                isExpired = true;
            }
            //System.out.println("isExpired:"+isExpired);
        } catch (Exception ex) {
            ex.printStackTrace(System.out);
            isExpired = true;
        }
        //System.out.println("isExpired:"+isExpired);
        return isExpired;
    }

    /**
     * This function will check wether the supplied URL is allowed to run
     * component or not.
     *
     * @param url a URL to check if its allowed to Run component or not.
     * @return boolean returns true if URL is valid , false otherwise.
     */
//    public static final boolean validateURL(URL url)
//    {
//        boolean isValid = false;
//        String toCompare=url.getProtocol()+"://"+url.getHost()+(url.getPort()!=80 ? ":"+url.getPort() : "");
//        System.out.println(toCompare);
//        String loopUrl=null;
//        for(int i=0;i<urls.length;i++)
//        {
//            loopUrl = urls[i];
//            if(loopUrl.equalsIgnoreCase(toCompare))
//            {
//                isValid = true;
//                break;
//            }
//        }
//        return isValid;
//    }

    /*
     *This function will check Expiry Date with system date where control is loaded and
     * return boolean value 
     * It will return true is component is expired or false otherwise
     * 
     */
    public static final boolean isComponentExpired() {
        return isComponentExpired(DateUtility.getDateStringFromDate(new Date(), DateUtility.DDMMYYPATTERN));
    }
}
