import com.webserver.SayWeb;

import javax.xml.ws.Endpoint;

/**
 * Created by sk-qianxiao on 2019/4/18.
 */
public class ServerMain {
    public static void main(String[] args) {
        String address = "http://localhost:8080/MyService/ServiceTest";
        Endpoint.publish(address, new SayWeb());
        System.out.println("publish success");
        //访问 http://localhost:8080/MyService/ServiceTest?wsdl
        //生成client代码 wsimport D:// -p com.abc http://localhost:8080/MyService/ServiceTest?wsdl
//        try {
//            System.in.read();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }
}
