/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.secuotp.service;

import com.secuotp.model.XMLParam;
import com.secuotp.model.XMLRequest;
import com.secuotp.model.XMLResponse;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

/**
 *
 * @author zenology
 */
public class SecuOTPService {
    private final String SITE_NAME = "https://128.199.82.168/SecuOTP-Service/";
    private String domain;
    private String serialNumber;

    public SecuOTPService(String domain, String serialNumber) throws NoSuchAlgorithmException, KeyManagementException {
        this.domain = domain;
        this.serialNumber = serialNumber;

        SSLContext sc = SSLContext.getInstance("TLS");
        sc.init(null, new TrustManager[]{new TrustAllX509TrustManager()}, new java.security.SecureRandom());
        HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
        HttpsURLConnection.setDefaultHostnameVerifier(new HostnameVerifier() {
            @Override
            public boolean verify(String string, SSLSession ssls) {
                return true;
            }
        });
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public int registerEndUser(String username, String email, String firstName, String lastName, String phone) {
        XMLRequest req = new XMLRequest();
        req.setService(XMLParam.M01);
        req.setDomainName(this.domain);
        req.setSerialNumber(this.serialNumber);
        req.setParamTag(new ArrayList());
        req.addChildTag("username", username);
        req.addChildTag("email", email);
        req.addChildTag("fname", firstName);
        req.addChildTag("lname", lastName);
        req.addChildTag("phone", phone);

        XMLResponse result = Service.sendPOST(req, SITE_NAME + "manage/register/end-user");
        return result.getStatus();
    }
    
    public int disableEndUser(String username, String removalCode) {
        XMLRequest req = new XMLRequest();
        req.setService(XMLParam.M02);
        req.setDomainName(this.domain);
        req.setSerialNumber(this.serialNumber);
        req.setParamTag(new ArrayList());
        req.addChildTag("username", username);
        req.addChildTag("code", removalCode);
        

        XMLResponse result = Service.sendPOST(req, SITE_NAME + "manage/disable/end-user");
        return result.getStatus();
    }
    
    public int generateOneTimePassword(String username) {
        XMLRequest req = new XMLRequest();
        req.setService(XMLParam.G01);
        req.setDomainName(this.domain);
        req.setSerialNumber(this.serialNumber);
        req.setParamTag(new ArrayList());
        req.addChildTag("username", username);
        

        XMLResponse result = Service.sendPOST(req, SITE_NAME + "otp/generate");
        return result.getStatus();
    }
}

class TrustAllX509TrustManager implements X509TrustManager {

    @Override
    public X509Certificate[] getAcceptedIssuers() {
        return new X509Certificate[0];
    }

    @Override
    public void checkClientTrusted(java.security.cert.X509Certificate[] certs,
            String authType) {
    }

    @Override
    public void checkServerTrusted(java.security.cert.X509Certificate[] certs,
            String authType) {
    }

}
