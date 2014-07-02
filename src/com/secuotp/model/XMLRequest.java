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
public class XMLRequest extends XMLReqRes {
    private int mode;
    private String domainName;
    private String serialNumber;
    private XMLParameter parameter;
    private XMLParameter changeTag;

    public String getDomainName() {
        return domainName;
    }

    public void setDomainName(String domainName) {
        this.domainName = domainName;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public XMLParameter getParameter() {
        return parameter;
    }

    public void setParameter(XMLParameter parameter) {
        this.parameter = parameter;
    }

    public XMLParameter getChangeTag() {
        return changeTag;
    }

    public void setChangeTag(XMLParameter changeTag) {
        this.changeTag = changeTag;
    }
    
    public void changeMode(String mode){
        
    } 
}
