/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.secuotp.model;

import java.util.ArrayList;

/**
 *
 * @author zenology
 */
public class XMLRequest {
    private String serviceId;
    private String domainName;
    private String serialNumber;
    private int pointer = 0;
    private ArrayList<XMLTag> paramTag;

    public XMLRequest() {
        this.setServiceId("");
        domainName = "";
        serialNumber = "";
        paramTag = new ArrayList<>();
    }
    
    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    public ArrayList<XMLTag> getParamTag() {
        return paramTag;
    }

    public void setParamTag(ArrayList<XMLTag> patamTag) {
        this.paramTag = patamTag;
    }

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

    public XMLTag getChildTag(int item) {
        return paramTag.get(item);
    }

    public void addChildTag(String tagName, String value) {
        this.paramTag.add(new XMLTag(tagName, value));
    }

    public XMLTag addChildTag(String tagName) {
        this.paramTag.add(new XMLTag(tagName, new ArrayList<XMLTag>()));
        return paramTag.get(paramTag.size() - 1);
    }

    private String setParameter(XMLTag tag) {
        if (tag.haveChildNode()) {
            ArrayList item = tag.getChildNode();
            String subTag1 = "<" + tag.getTagName() + ">";
            String values = "";
            for (int i = 0; i < tag.getChildNode().size(); i++)
            {
                try
                {
                    XMLTag item2 = (XMLTag) item.get(i);
                    pointer++;
                    values = values + setParameter(item2);
                }
                catch (ArrayIndexOutOfBoundsException e)
                {
                    System.out.println(e);
                }
            }
            String subTag2 = "</" + tag.getTagName() + ">";
            return subTag1 + values + subTag2;
        } else {
            return "<" + tag.getTagName() + ">" + tag.getValue() + "</" + tag.getTagName() + ">";
        }
    }

    @Override
    public String toString() {
        String xml = "<?xml version=\"1.0\"?>"+"<secuotp>"
                +"<service sid=\""+getServiceId()+"\">"+StringText.getServiceName(getServiceId())+"</service>"
                +"<authentication>"+"<domain>"+domainName+"</domain>"
                +"<serial>"+serialNumber+"</serial>"+"</authentication>"+
                "<parameter>";

            String param = "";

            for (int i = 0; i < this.paramTag.size(); i++) {
                try
                {
                    pointer++;
                    XMLTag tag = (XMLTag) paramTag.get(i);
                    param = param + setParameter(tag);
                }
                catch (ArrayIndexOutOfBoundsException e)
                {
                    System.out.println(e);
                }
            }

            String buttomXml = "</parameter></secuotp>";
            String combine = xml + param + buttomXml;
            
            return combine;
    }

}
