/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.secuotp.xml;

import com.secuotp.model.Parameter;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.tree.DefaultDocument;

/**
 *
 * @author zenology
 */
public class XMLCreate {
    public static final String REGISTER_END_USER = "S-01";
    
    private static final String REGISTER_END_USER_NAME = "Register End-User";
    
    /**
     * Return XML String for the Web Service <b>Services</b>
     * 
     * @param service Service ID Can get by <b>XMLCreate.[Name of Service]</b>
     * @param siteAuthentication Site info
     * @param userInfo User Info Parameter
     * @return The String of XML for Web Service    
     */
    public static String createRequest(String service, Parameter siteAuthentication, Parameter userInfo){
        Document doc = new DefaultDocument();
        Element rootNode = doc.addElement("secuotp");
        Element serviceNode = rootNode.addElement("service");
            serviceNode.addAttribute("sid", service);
            serviceNode.addText(getServiceName(service));
        Element authenticationNode = rootNode.addElement("authentication");
        while(siteAuthentication.hasNext()){
            String[] text = siteAuthentication.pop();
            authenticationNode.addElement(text[0]).addText(text[1]);
        }
        Element parameterNode = rootNode.addElement("parameter");
        while(userInfo.hasNext()){
            String[] text = userInfo.pop();
            parameterNode.addElement(text[0]).addText(text[1]);
        }
        
        doc.normalize();
        return doc.asXML();
    }
    
    private static String getServiceName(String service){
        if(service.equals(REGISTER_END_USER)){
            return REGISTER_END_USER_NAME;
        }
        return null;
    }
}
