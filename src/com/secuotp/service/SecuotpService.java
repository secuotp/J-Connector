/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.secuotp.service;

import com.secuotp.model.XMLRequest;
import com.secuotp.model.XMLResponse;
import java.net.URL;

/**
 *
 * @author zenology
 */
public class SecuotpService {

    public static XMLResponse sendResponse(XMLRequest request, URL serviceUrl) {
        return new XMLResponse();
    }
}
