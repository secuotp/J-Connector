/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.secuotp.service;

import com.secuotp.model.ServiceStatus;
import com.secuotp.model.DoubleArrayList;
import com.secuotp.model.User;
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

    public ServiceStatus registerEndUser(String username, String email, String firstName, String lastName, String phone) {
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
        if (result != null) {
            return new ServiceStatus(result.getStatus(), result.getMessage());
        }
        return null;
    }

    public ServiceStatus disableEndUser(String username, String removalCode) {
        XMLRequest req = new XMLRequest();
        req.setService(XMLParam.M02);
        req.setDomainName(this.domain);
        req.setSerialNumber(this.serialNumber);
        req.setParamTag(new ArrayList());
        req.addChildTag("username", username);
        req.addChildTag("code", removalCode);

        XMLResponse result = Service.sendPOST(req, SITE_NAME + "manage/disable/end-user");
        if (result != null) {
            return new ServiceStatus(result.getStatus(), result.getMessage());
        }
        return null;
    }

    public ServiceStatus generateOneTimePassword(String username) {
        XMLRequest req = new XMLRequest();
        req.setService(XMLParam.G01);
        req.setDomainName(this.domain);
        req.setSerialNumber(this.serialNumber);
        req.setParamTag(new ArrayList());
        req.addChildTag("username", username);

        XMLResponse result = Service.sendPOST(req, SITE_NAME + "otp/generate");
        if (result != null) {
            return new ServiceStatus(result.getStatus(), result.getMessage());
        }
        return null;
    }

    public ServiceStatus authenticateOneTimePassword(String username, String otp) {
        XMLRequest req = new XMLRequest();
        req.setService(XMLParam.A01);
        req.setDomainName(this.domain);
        req.setSerialNumber(this.serialNumber);
        req.setParamTag(new ArrayList());
        req.addChildTag("username", username);
        req.addChildTag("password", otp);

        XMLResponse result = Service.sendPOST(req, SITE_NAME + "otp/authenticate");
        if (result != null) {
            return new ServiceStatus(result.getStatus(), result.getMessage());
        }
        return null;
    }

    // if type = 0 is Full 1 is Default
    public ServiceStatus getUserInfo(String username, int type) {
        XMLRequest req = new XMLRequest();
        req.setService(XMLParam.U01);
        req.setDomainName(this.domain);
        req.setSerialNumber(this.serialNumber);
        req.setParamTag(new ArrayList());
        req.addChildTag("username", username);

        String typeString = "default";
        if (type == 0) {

        } else if (type == 1) {
            typeString = "full";
        }
        req.addChildTag("type", typeString);

        XMLResponse response = Service.sendPOST(req, SITE_NAME + "user/end-user");
        DoubleArrayList list = response.getParameter();
        User u = new User();
        u.setUsername(list.getValue("username"));
        u.setEmail(list.getValue("email"));
        u.setFirstName(list.getValue("fname"));
        u.setLastName(list.getValue("lname"));
        u.setPhone(list.getValue("phone"));
        if (type == 1) {
            u.setRemovalCode(list.getValue("removal"));
            u.setSerialNumber(list.getValue("serial"));
        }

        ServiceStatus status = new ServiceStatus(response.getStatus(), response.getMessage());
        status.setData(u);

        return status;
    }

    // channel == 0 is sms, 1 is mobile

    public String sendMigrationRequest(String username) {
        XMLRequest req = new XMLRequest();
        req.setService(XMLParam.O01);
        req.setDomainName(this.domain);
        req.setSerialNumber(this.serialNumber);
        req.addChildTag("username", username);
        
        XMLResponse response = Service.sendPOST(req, SITE_NAME + "otp/migrate");
        DoubleArrayList list = response.getParameter();
        
        return list.getValue("migration-code");
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
