/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.secuotp.model;

import java.io.IOException;
import java.io.StringReader;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

/**
 *
 * @author Zenology
 */
class XMLParser {

    private Document doc;
    private NodeList list;
    private Element e;

    public XMLParser(String xml) {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            doc = builder.parse(new InputSource(new StringReader(xml)));
        } catch (ParserConfigurationException | SAXException | IOException ex) {
            Logger.getLogger(XMLParser.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String getDataFromTag(String tagName, int numberItem) {
        list = doc.getElementsByTagName(tagName);
        if (list.getLength() > 0) {
            Node n = list.item(numberItem);
            return n.getTextContent();
        } else {
            return null;
        }
    }

    public static String[] getChildData(NodeList nList, int numberItem) {
        String[] data = new String[2];
        Node n = nList.item(0).getFirstChild();
        for (int i = 0; i < numberItem; i++) {
            n = n.getNextSibling();
        }
        data[0] = n.getNodeName();
        data[1] = n.getTextContent();
        return data;
    }

    public NodeList getNodeFromTag(String tagname) {
        return doc.getElementsByTagName(tagname);

    }

    public String getAttibuteFromTag(String tagName, String attibuteName, int numberItem) {
        list = doc.getElementsByTagName(tagName);
        if (list.getLength() > 0) {
            e = (Element) list.item(numberItem);
            return e.getAttribute(attibuteName);
        } else {
            return null;
        }
    }

    public int getChildItem(String tagName, int numberItem) {
        list = doc.getElementsByTagName(tagName);
        e = (Element) list.item(numberItem);
        if (e == null) {
            return 0;
        }
        return e.getChildNodes().getLength();
    }

    public int getNumberItem(String tagName) {
        list = doc.getElementsByTagName(tagName);
        return list.getLength();
    }
}