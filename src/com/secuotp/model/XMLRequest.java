/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.secuotp.model;

import java.util.ArrayList;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.tree.DefaultDocument;

/**
 *
 * @author zenology
 */
public class XMLRequest {
    private String serviceId;
    private String domainName;
    private String serialNumber;
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

    private void setParameter(Element parentNode, XMLTag tag) {
        Element e = parentNode.addElement(tag.getTagName());
        if (tag.haveChildNode()) {
            for (int i = 0; i < tag.getChildNode().size(); i++) {
                setParameter(e, tag.getChildNode().get(i));
            }
        } else {
            e.setText(tag.getValue());
        }
    }

    @Override
    public String toString() {
        Document doc = new DefaultDocument();
        Element root = doc.addElement("secuotp");
        Element serviceNode = root.addElement("service");
        serviceNode.addAttribute("sid", getServiceId());
        serviceNode.setText(StringText.getServiceName(getServiceId()));
        Element authenNode = root.addElement("authentication");
        authenNode.addElement("domain").setText(domainName);
        authenNode.addElement("serial").setText(serialNumber);
        Element paramNode = root.addElement("parameter");
        for (int i = 0; i < this.paramTag.size(); i++) {
            setParameter(paramNode, this.paramTag.get(i));
        }
        doc.normalize();
        return doc.asXML();
    }

}
