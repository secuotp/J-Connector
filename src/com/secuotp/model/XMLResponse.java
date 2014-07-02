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
public class XMLResponse extends XMLReqRes{
    private String message;
    private XMLParameter parameter;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public XMLParameter getParameter() {
        return parameter;
    }

    public void setParameter(XMLParameter parameter) {
        this.parameter = parameter;
    }
    
}
