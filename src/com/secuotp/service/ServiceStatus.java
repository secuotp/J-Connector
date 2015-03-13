/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.secuotp.service;

/**
 *
 * @author zenology
 */
public class ServiceStatus {
    private int statusId;
    private String statusText;

    public ServiceStatus(int statusId, String statusText) {
        this.statusId = statusId;
        this.statusText = statusText;
    }
    
    public int getStatusId() {
        return statusId;
    }

    public void setStatusId(int statusId) {
        this.statusId = statusId;
    }

    public String getStatusText() {
        return statusText;
    }

    public void setStatusText(String statusText) {
        this.statusText = statusText;
    }
    
    
            
}
