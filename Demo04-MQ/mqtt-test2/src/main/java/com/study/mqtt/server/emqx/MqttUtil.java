package com.study.mqtt.server.emqx;

import javax.net.ssl.*;
import java.io.FileInputStream;
import java.security.KeyStore;
import java.security.SecureRandom;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;

public class MqttUtil {
    public static SSLSocketFactory getSSLSocktet(boolean trustAll, String caPath) {
        try {
            // finally, create SSL socket factory
            SSLContext context = SSLContext.getInstance("TLSv1.2");
            if (trustAll) {
                //创建管理器
                TrustManager[] trustAllCerts = new TrustManager[]{new X509TrustManager() {
                    @Override
                    public void checkClientTrusted(java.security.cert.X509Certificate[] x509Certificates, String s) {
                    }

                    @Override
                    public void checkServerTrusted(java.security.cert.X509Certificate[] x509Certificates, String s) {
                    }

                    @Override
                    public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                        return new java.security.cert.X509Certificate[]{};
                    }
                }};
                context.init(null, trustAllCerts, new SecureRandom());
            } else {
                // CA certificate is used to authenticate server
                CertificateFactory cAf = CertificateFactory.getInstance("X.509");
                FileInputStream caIn = new FileInputStream(caPath);
                X509Certificate ca = (X509Certificate) cAf.generateCertificate(caIn);
                KeyStore caKs = KeyStore.getInstance("JKS");
                caKs.load(null, null);
                caKs.setCertificateEntry("ca-certificate", ca);
                TrustManagerFactory tmf = TrustManagerFactory.getInstance("PKIX");
                tmf.init(caKs);

                context.init(null, tmf.getTrustManagers(), new SecureRandom());
            }


            return context.getSocketFactory();
        } catch (Exception e) {

        }
        return null;
    }
}
