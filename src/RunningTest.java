/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import com.secuotp.service.SecuOTPService;
import com.secuotp.model.ServiceStatus;

/**
 *
 * @author Polwath-2
 */

public class RunningTest {

    public static void main(String[] args) throws Exception {
    
        SecuOTPService service = new SecuOTPService("https://secuotp-test.co.th", "5L44G-7XR1G-V5RAM-JC6KG");
        ServiceStatus status = service.getUserInfo("zenology", 1);
        Object o = status.getData();
 
        /*if(status.getStatusId() == 100){
            System.out.println("Good");
        }else {
            System.err.println(status.getStatusText());
        }
                */
    }

}
