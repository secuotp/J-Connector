/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.secuotp.service;

import com.secuotp.model.XMLParameter;
import com.secuotp.model.XMLRequest;
import com.secuotp.model.XMLResponse;
import com.secuotp.model.XMLTag;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.representation.Form;
import java.util.ArrayList;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author zenology
 */
public class SecuotpService {

    public static XMLResponse sendResponse(XMLRequest request, String serviceUrl, String method) {
        Client c = new Client();

        WebResource resource = c.resource(serviceUrl);
        Form f = new Form();
        f.add("request", request.toString());
        ClientResponse response = null;

        if (method.equalsIgnoreCase("POST")) {
            response = resource.type(MediaType.APPLICATION_XML_TYPE).post(ClientResponse.class, f);
        } else if (method.equalsIgnoreCase("GET")) {
            response = resource.type(MediaType.APPLICATION_XML_TYPE).get(ClientResponse.class);
        } else if (method.equalsIgnoreCase("PUT")) {
            response = resource.type(MediaType.APPLICATION_XML_TYPE).put(ClientResponse.class, f);
        } else if (method.equalsIgnoreCase("DELETE")) {
            response = resource.type(MediaType.APPLICATION_XML_TYPE).delete(ClientResponse.class, f);
        }
        if (response.getStatus() < 300) {
            return new XMLResponse(response.getEntity(String.class));
        }
        return null;
    }

    public static XMLResponse sendResponse(String request, String serviceUrl, String method) {
        Client c = new Client();

        WebResource resource = c.resource(serviceUrl);
        Form f = new Form();
        f.add("request", request);
        ClientResponse response = null;

        if (method.equalsIgnoreCase("POST")) {
            response = resource.type(MediaType.APPLICATION_XML_TYPE).post(ClientResponse.class, f);
        } else if (method.equalsIgnoreCase("GET")) {
            response = resource.type(MediaType.APPLICATION_XML_TYPE).get(ClientResponse.class);
        } else if (method.equalsIgnoreCase("PUT")) {
            response = resource.type(MediaType.APPLICATION_XML_TYPE).put(ClientResponse.class, f);
        } else if (method.equalsIgnoreCase("DELETE")) {
            response = resource.type(MediaType.APPLICATION_XML_TYPE).delete(ClientResponse.class, f);
        }
        if (response.getStatus() < 300) {
            return new XMLResponse(response.getEntity(String.class));
        }
        return null;
    }

    public static void main(String[] args) {
        XMLRequest req = new XMLRequest();
        req.setSid("U-01");
        req.setDomainName("http://sit.kmutt.ac.th");
        req.setSerialNumber("2J2FC-A3ZC5-44DQH-VTE5H");
        req.setParamTag(new ArrayList<XMLTag>());
        req.addChildTag("username", "Alpaca");
        XMLTag changeTag = req.addChildTag("change");
        changeTag.addChildTag("param", "email");
        changeTag.addChildTag("value", "alpaca@zoo.com");

        XMLResponse response = sendResponse(req, "http://secuotp.sit.kmutt.ac.th/SecuOTP-Service/user/end-user", "PUT");
        XMLParameter param = response.getParameter();
        while (param.hasNext()) {
            String[] a = param.pop();
            System.out.println(a[0] + "\t" + a[1]);
        }
    }

}
