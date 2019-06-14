package com.xq.study.jdk.http;

import javax.net.ssl.*;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

 class NullHostNameVerifier implements HostnameVerifier {
    /*
     * (non-Javadoc)
     *
     * @see javax.net.ssl.HostnameVerifier#verify(java.lang.String,
     * javax.net.ssl.SSLSession)
     */
    @Override
    public boolean verify(String arg0, SSLSession arg1) {
        // TODO Auto-generated method stub
        return true;
    }
}

public class HttpsGet implements X509TrustManager {
    @Override
    public void checkClientTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {
        // TODO Auto-generated method stub

    }

    @Override
    public void checkServerTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {
        // TODO Auto-generated method stub

    }

    @Override
    public X509Certificate[] getAcceptedIssuers() {
        // TODO Auto-generated method stub
        return null;
    }

    public static String httpsRequest(String requestUrl, String requestMethod, String outputStr) {
        StringBuffer buffer = null;
        try {
            HttpsURLConnection.setDefaultHostnameVerifier(new NullHostNameVerifier()); //忽略证书

            //创建SSLContext
            SSLContext sslContext = SSLContext.getInstance("SSL");
            TrustManager[] tm = {new HttpsGet()};
            //初始化
            sslContext.init(null, tm, new java.security.SecureRandom());
            ;
            //获取SSLSocketFactory对象
            SSLSocketFactory ssf = sslContext.getSocketFactory();
            URL url = new URL(requestUrl);
            HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setUseCaches(false);
            conn.setRequestMethod(requestMethod);
            //设置当前实例使用的SSLSoctetFactory
            conn.setSSLSocketFactory(ssf);
            conn.connect();
            //往服务器端写内容
            if (null != outputStr) {
                OutputStream os = conn.getOutputStream();
                os.write(outputStr.getBytes("utf-8"));
                os.close();
            }

            //读取服务器端返回的内容
            InputStream is = conn.getInputStream();
            InputStreamReader isr = new InputStreamReader(is, "utf-8");
            BufferedReader br = new BufferedReader(isr);
            buffer = new StringBuffer();
            String line = null;
            while ((line = br.readLine()) != null) {
                buffer.append(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return buffer.toString();
     }

    public static void main(String[] args) throws Exception {
        String s = httpsRequest("https://192.168.20.46:10099/device/getDeviceExtras", "GET", null);
        System.out.println(s);

        String s1 = httpsRequest("https://inner.iot-itf.com:10099/device/saveDevice", "POST", "{\n" +
                "\t\"importKeyList\": [{\n" +
                "\t\t\"addressable\": {\n" +
                "\t\t\t\"name\": \"device-simple-No90\",\n" +
                "\t\t\t\"method\": \"POST\",\n" +
                "\t\t\t\"protocol\": \"HTTP\",\n" +
                "\t\t\t\"address\": \"192.168.20.46\",\n" +
                "\t\t\t\"port\": 4003,\n" +
                "\t\t\t\"publisher\": \"fg\",\n" +
                "\t\t\t\"user\": \"fg\",\n" +
                "\t\t\t\"password\": \"fg\",\n" +
                "\t\t\t\"topic\": \"fg\"\n" +
                "\t\t},\n" +
                "\t\t\"device\": {\n" +
                "\t\t\t\"name\": \"device-name-test15\",\n" +
                "\t\t\t\"description\": \"test\",\n" +
                "\t\t\t\"adminState\": \"UNLOCKED\",\n" +
                "\t\t\t\"operatingState\": \"ENABLED\",\n" +
                "\t\t\t\"addressable\": {\n" +
                "\t\t\t\t\"name\": \"device-simple-No90\"\n" +
                "\t\t\t},\n" +
                "\t\t\t\"labels\": [],\n" +
                "\t\t\t\"service\": {\n" +
                "\t\t\t\t\"name\": \"device-simple\"\n" +
                "\t\t\t},\n" +
                "\t\t\t\"profile\": {\n" +
                "\t\t\t\t\"name\": \"RandNum-Device-Test11\"\n" +
                "\t\t\t}\n" +
                "\t\t}\n" +
                "\t}]\n" +
                "}");
        System.out.println(s1);
    }
}
