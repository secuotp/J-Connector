/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.secuotp.service;

import com.secuotp.model.XMLRequest;
import com.secuotp.model.XMLResponse;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.representation.Form;
import javax.ws.rs.core.MediaType;

/**
 * 
 * @author zenology
 */
public class SecuotpService {
    /**
     * Send the parameter to SecuOTP Web Service and get the {@link XMLResponse} from the Web Service <br/>
     * <pre>
     * Example:
     * {@code
     *  XMLRequest request = new XMLRequest();
     *  request.setSid("U-01");
     *  request.setDomainName("https://secuotp.sit.kmutt.ac.th");
     *  request.setSerialNumber("XXXXX-XXXXX-XXXXX-XXXXX");
     *  request.addChildTag("username", "XX");
     *  request.addChildTag("type", "default");
     * 
     * XMLResponse response = SecuotpService.sendResponse(request, RequestURL.U_01, "PUT");
     * }
     * </pre>
     * @param request The Request Parameter required for Web Service
     * @param serviceUrl The URL of Web Service
     * @param method The String of HTTP Method (ex.POST, GET, PUT, DELETE)
     * @return The Response of the Web Service
     * 
     * @see RequestURL
     */
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

    /**
     * Send the parameter to SecuOTP Web Service and get the {@link XMLResponse} from the Web Service <br/>
     * <pre>
     * Example:
     * {@code
     *  String xml = "
     *      <secuotp>
     *          <service sid="U-01">Get End-User Data</service?
     *          <authentication>
     *              <domain>https://secuotp.sit.kmutt.ac.th</domain>
     *              <serial>XXXXX-XXXXX-XXXXX-XXXXX</serial>
     *          </authentication>
     *          <parameter>
     *              <username>XX</username>
     *              <type>default</type>
     *          </parameter>
     *      </secuotp>
     *  ";
     * XMLResponse response = SecuotpService.sendResponse(xml, RequestURL.U_01, "PUT");
     * }
     * </pre>
     * @param request The Request xml String required for Web Service
     * @param serviceUrl The URL of Web Service
     * @param method The String of HTTP Method (ex.POST, GET, PUT, DELETE)
     * @return The Response of the Web Service
     * 
     * @see RequestURL
     */
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
}
