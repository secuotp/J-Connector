/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.secuotp.model;

/**
 *
 * @author zenology
 */
public class XMLResponse {

    
    private int status;
    private String message;
    private DoubleArrayList parameter;

    public XMLResponse(String xml) {
        parameter = new DoubleArrayList();
        XMLParser parse = new XMLParser(xml);
        status = Integer.parseInt(parse.getAttibuteFromTag("secuotp", "status", 0));
        message = parse.getDataFromTag("message", 0);

        for (int i = 0; i < parse.getChildItem("response", 0); i++) {
            String[] data = XMLParser.getChildData(parse.getNodeFromTag("response"), i);
            parameter.add(data[0], data[1]);
        }
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    
    public DoubleArrayList getParameter() {
        return parameter;
    }

    public void setParameter(DoubleArrayList parameter) {
        this.parameter = parameter;
    }

}
