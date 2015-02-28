/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.secuotp.service;

import com.secuotp.model.DoubleArrayList;
import com.secuotp.model.XMLRequest;
import com.secuotp.model.XMLResponse;
import java.net.*;
import java.io.*;

/**
 *
 * @author zenology
 */
public class Service {

    public static XMLResponse send(DoubleArrayList parameter, String serviceUrl) throws Exception {
        String newUrl = serviceUrl + "?";
        while (parameter.hasNext()) {
            String[] paramUrl = parameter.pop();
            newUrl = newUrl + paramUrl[0] + "=" + paramUrl[1];

            if (parameter.hasNext()) {
                newUrl = newUrl + "&";
            }
        }
        
        URL u = new URL(newUrl);
        URLConnection uc = u.openConnection();
        
        HttpURLConnection con = (HttpURLConnection) uc;
        con.setRequestMethod("GET");
        
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
	String inputLine;
	StringBuilder response = new StringBuilder();
 
	while ((inputLine = in.readLine()) != null) {
		response.append(inputLine);
	}
        
        return new XMLResponse(response.toString());
    }

    public static XMLResponse sendPOST(XMLRequest request, String serviceUrl){
        try{
            URL u = new URL(serviceUrl);
            URLConnection uc = u.openConnection();

            HttpURLConnection con = (HttpURLConnection) uc;
            con.setRequestMethod("POST");
            con.setRequestProperty("Content-Type", "application/xml");
            String requesturl = "request="+request.toString();
            con.setDoOutput(true);
            
            DataOutputStream wr = new DataOutputStream(con.getOutputStream());
            wr.writeBytes(requesturl);
            wr.flush();
            wr.close();
            

            StringBuilder response;

            BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            response = new StringBuilder();
            while ((inputLine = br.readLine()) != null) {
                    response.append(inputLine);
            }

            return new XMLResponse(response.toString());
        }catch(Exception ex){
            System.out.println(ex);
            return null;
        }
    }
    
    public static XMLResponse sendPUT(XMLRequest request, String serviceUrl){
        try{
            URL u = new URL(serviceUrl);
            URLConnection uc = u.openConnection();

            HttpURLConnection con = (HttpURLConnection) uc;
            con.setRequestMethod("PUT");
            con.setRequestProperty("Content-Type","application/xml");
            String requesturl = "request="+request.toString();
            con.setDoOutput(true);
            
            DataOutputStream wr = new DataOutputStream(con.getOutputStream());
            wr.writeBytes(requesturl);
            wr.flush();
            wr.close();

            StringBuilder response;

            BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            response = new StringBuilder();
            while ((inputLine = br.readLine()) != null) {
                    response.append(inputLine);
            }

            return new XMLResponse(response.toString());
        }catch(Exception ex){
            System.out.println(ex);
            return null;
        }
    }
}
