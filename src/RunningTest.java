/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import com.secuotp.model.XMLParam;
import com.secuotp.model.XMLRequest;
import com.secuotp.model.XMLResponse;
import com.secuotp.service.SecuOTPService;
import com.secuotp.service.Service;
import java.util.ArrayList;

/**
 *
 * @author Polwath-2
 */

public class RunningTest {

    public static void main(String[] args) throws Exception {
    
        SecuOTPService service = new SecuOTPService("https://secuotp-test.co.th", "5L44G-7XR1G-V5RAM-JC6KG");
        if(service.generateOneTimePassword("xterma") == 100){
            System.out.println("Good");
        }else {
            System.err.println("Bad");
        }

    }

}
