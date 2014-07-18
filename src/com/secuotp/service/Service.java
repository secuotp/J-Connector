/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.secuotp.service;

import com.secuotp.model.DoubleArrayList;
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
public class Service {

    public static XMLResponse send(DoubleArrayList parameter, String serviceUrl) {
        Client c = new Client();

        String newUrl = serviceUrl + "?";
        while (parameter.hasNext()) {
            String[] paramUrl = parameter.pop();
            newUrl = newUrl + paramUrl[0] + "=" + paramUrl[1];

            if (parameter.hasNext()) {
                newUrl = newUrl + "&";
            }
        }

        WebResource resource = c.resource(newUrl);

        ClientResponse response = resource.type(MediaType.APPLICATION_XML_TYPE).get(ClientResponse.class);
        return new XMLResponse(response.getEntity(String.class));
    }

    public static XMLResponse sendPOST(XMLRequest request, String serviceUrl) {
        Client c = new Client();

        WebResource resource = c.resource(serviceUrl);
        Form f = new Form();
        f.add("request", request);

        ClientResponse response = resource.type(MediaType.APPLICATION_XML_TYPE).post(ClientResponse.class, f);
        return new XMLResponse(response.getEntity(String.class));
    }
    
    public static XMLResponse sendPUT(XMLRequest request, String serviceUrl) {
        Client c = new Client();

        WebResource resource = c.resource(serviceUrl);
        Form f = new Form();
        f.add("request", request);

        ClientResponse response = resource.type(MediaType.APPLICATION_XML_TYPE).put(ClientResponse.class, f);
        return new XMLResponse(response.getEntity(String.class));
    }
}
