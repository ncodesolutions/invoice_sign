/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ncode.invoice.pki.util;

import java.io.File;

/**
 *
 
 */
public class ListFileUtil {

    File[] list;

    public File[] listAllFiles(String path) {
        File root = new File(path);
        File[] list = root.listFiles();
        if (list != null) {
            for (File f : list) {
                if (f.isDirectory()) {
                    listAllFiles(f.getAbsolutePath());
                }
            }
        }
        return list;
    }
}
