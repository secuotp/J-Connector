/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import com.secuotp.model.XMLRequest;
import com.secuotp.model.XMLResponse;
import com.secuotp.service.RequestURL;
import com.secuotp.service.Service;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

/**
 *
 * @author Polwath-2
 */
class TrustAllX509TrustManager implements X509TrustManager {
    @Override
    public X509Certificate[] getAcceptedIssuers() {
        return new X509Certificate[0];
    }

    @Override
    public void checkClientTrusted(java.security.cert.X509Certificate[] certs,
            String authType) {
    }

    @Override
    public void checkServerTrusted(java.security.cert.X509Certificate[] certs,
            String authType) {
    }

}

public class test {
    public static void main(String[] args) throws Exception{
        SSLContext sc = SSLContext.getInstance("TLS");
        sc.init(null, new TrustManager[] { new TrustAllX509TrustManager() }, new java.security.SecureRandom());
        HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
        HttpsURLConnection.setDefaultHostnameVerifier( new HostnameVerifier(){
            @Override
            public boolean verify(String string,SSLSession ssls) {
                return true;
            }
        });
        
        XMLRequest req = new XMLRequest();
        req.setServiceId("U-01");
        req.setDomainName("p");
        req.setSerialNumber("NS65P-MG115-KU09A-GZXMM");
        req.setParamTag(new ArrayList());
        req.addChildTag("username", "polwath");
        req.addChildTag("type", "default");
        
        XMLResponse result = Service.sendPOST(req, 
                "https://secure.secuotp.co.th/SecuOTP-Service/user/end-user");
        
        System.out.println(req.toString());
        System.out.println(result.getStatus()+" - "+result.getMessage());
        
        while(result.getParameter().hasNext()){
              String[] a = result.getParameter().pop();
              System.out.println(a[0]+"\t"+a[1]);
        }
    }
}
