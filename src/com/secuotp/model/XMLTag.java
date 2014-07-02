/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.secuotp.model;

import java.util.ArrayList;

/**
 *
 * @author Zenology
 */
public class XMLTag {

    private String tagName;
    private String value;
    private ArrayList<XMLTag> childNode;

    public XMLTag(String tagName, String value) {
        this.tagName = tagName;
        this.value = value;
    }

    public XMLTag(String tagName, ArrayList<XMLTag> childNode) {
        this.tagName = tagName;
        this.childNode = childNode;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public ArrayList<XMLTag> getChildNode() {
        return childNode;
    }

    public void setChildNode(ArrayList<XMLTag> childNode) {
        this.childNode = childNode;
    }

    public boolean haveChildNode() {
        return childNode != null;
    }

    public XMLTag getChildTag(int item) {
        return childNode.get(item);
    }
    
    public void addChildTag(String tagName, String value){
        this.childNode.add(new XMLTag(tagName, value));
    }
    
    public XMLTag addChildTag(String tagName){
        this.childNode.add(new XMLTag(tagName, new ArrayList<XMLTag>()));
        return childNode.get(childNode.size() - 1);
    }
}
