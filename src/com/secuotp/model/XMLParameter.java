/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.secuotp.model;

import java.util.ArrayList;

/**
 * 
 * @author Panasan Sinroungorng
 * 
 */
public class XMLParameter {
    private ArrayList<String> keyList;
    private ArrayList<String> valueList;
    private int pointer = 0;

    public XMLParameter() {
        keyList = new ArrayList<>();
        valueList = new ArrayList<>();
    }

    /**
     * add Parameter Value
     *
     * @param key name of the Parameter
     * @param value value of the Parameter
     */
    public void add(String key, String value) {
        this.keyList.add(key);
        this.valueList.add(value);
    }

    /**
     * Get Parameter Data from List and move the pointer index by 1
     * @return parameter data
     */
    public String[] pop() {
        try {
            String[] valueString = new String[2];
            valueString[0] = keyList.get(pointer);
            valueString[1] = valueList.get(pointer);
            pointer++;
            return valueString;
        } catch (ArrayIndexOutOfBoundsException e) {
            System.err.println(e.getCause());
        }
        return null;
    }
    
    /**
     * Get Parameter Data but the pointer still not moving
     * @return parameter data
     */
    public String[] peek(){
        try {
            String[] valueString = new String[2];
            valueString[0] = keyList.get(pointer);
            valueString[1] = valueList.get(pointer);
            return valueString;
        } catch (ArrayIndexOutOfBoundsException e) {
            System.err.println(e.getCause());
        }
        return null;
    }
    
    /**
     * Get if the pointer reach the last index
     * @return boolean of last index
     */
    public boolean hasNext(){
        return pointer < keyList.size();
    }
    
    /**
     * Move the pointer to the first index
     */
    public void first(){
        pointer = 0;
    }
    
    /**
     * Move the pointer to the last index
     */
    public void last(){
        pointer = keyList.size();
    }
    
    /**
     * get the parameter value form @param key
     * @param key the parameter name
     * @return parameter data
     */
    public String getValue(String key){
        int p = 0;
        while(p < keyList.size()){
            if(keyList.get(p).equals(key)){
                return valueList.get(p);
            }else{
                p++;
            }
        }
        return null;
    }
    
    /**
     * clear the list
     */
    public void clear(){
        keyList = new ArrayList<>();
        valueList = new ArrayList<>();
        this.first();
    }
}
