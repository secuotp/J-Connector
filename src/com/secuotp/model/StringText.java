/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.secuotp.model;

/**
 *
 * @author Zenology
 */
class StringText {

    private static final String M01 = "Register End-User";
    private static final String M02 = "Disable End-User";
    private static final String G01 = "Generate One-Time Password";
    private static final String A01 = "Authenticate One-Time Password";
    private static final String O01 = "Migrate One-Time Password Channel";
    private static final String O02 = "Time Sync";
    private static final String U01 = "Get End-User Data";
    private static final String U02 = "Set End-User Data";

    protected static String getServiceName(String sid) {
        switch (sid) {
            case "M-01":
                return M01;
            case "M-02":
                return M02;
            case "G-01":
                return G01;
            case "A-01":
                return A01;
            case "O-01":
                return O01;
            case "O-02":
                return O02;
            case "U-01":
                return U01;
            case "U-02":
                return U02;
        }
        return null;
    }
}
